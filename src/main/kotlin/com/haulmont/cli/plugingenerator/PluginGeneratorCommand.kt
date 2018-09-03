package com.haulmont.cli.plugingenerator

import com.beust.jcommander.Parameters
import com.haulmont.cuba.cli.Resources
import com.haulmont.cuba.cli.WorkingDirectoryManager
import com.haulmont.cuba.cli.commands.GeneratorCommand
import com.haulmont.cuba.cli.generation.TemplateProcessor
import com.haulmont.cuba.cli.kodein
import com.haulmont.cuba.cli.prompting.Answers
import com.haulmont.cuba.cli.prompting.QuestionsList
import org.kodein.di.generic.instance
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermission

@Parameters(commandDescription = "Generates empty CUBA CLI plugin project")
class PluginGeneratorCommand : GeneratorCommand<PluginModel>() {

    private val workingDirectoryManager: WorkingDirectoryManager by kodein.instance()

    private val resources: Resources by Resources.fromMyPlugin()

    override fun getModelName(): String = "plugin"

    override fun QuestionsList.prompting() {
        question("name", "Plugin project name") {
            default("sample-plugin")

            validate {
                workingDirectoryManager.workingDirectory.resolve(value).let {
                    if (Files.exists(it))
                        fail("Directory with name $value already exists")
                }
            }
        }

        question("group", "Group") {
            default("com.company")

            validate {
                checkIsPackage()
            }
        }

        question("rootPackage", "Plugin root package") {
            default {
                val pluginName = (it["name"] as String).replace("[^\\w]".toRegex(), "").toLowerCase()
                "${it["group"]}.$pluginName"
            }

            validate {
                checkIsPackage()
            }
        }
        question("className", "Plugin class name") {
            validate {
                checkIsClass()

                if (!value.endsWith("Plugin")) {
                    fail("Plugin class name must end with `Plugin` postfix")
                }
            }
        }
        question("moduleName", "Plugin java module name") {
            default {
                it["rootPackage"] as String
            }
        }
    }

    override fun createModel(answers: Answers): PluginModel = PluginModel(answers)

    override fun generate(bindings: Map<String, Any>) {
        workingDirectoryManager.workingDirectory = Paths.get(model.name)
        val cwd = workingDirectoryManager.workingDirectory

        Files.createDirectories(cwd)

        TemplateProcessor(resources.getTemplate("plugintemplate"), bindings) {
            listOf("src", "build.gradle", "settings.gradle").forEach {
                transform(it)
            }

            listOf("gradle", "gradlew", "gradlew.bat").forEach {
                copy(it)
            }
            try {
                Files.setPosixFilePermissions(cwd.resolve("gradlew"), setOf(PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_READ))
            } catch (e: Exception) {}
        }
    }
}
