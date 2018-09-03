package ${plugin.rootPackage}

import com.beust.jcommander.Parameters
import com.haulmont.cuba.cli.commands.CliCommand
import com.haulmont.cuba.cli.kodein
import org.kodein.di.generic.instance
import java.io.PrintWriter

@Parameters(commandDescription = "Command does nothing")
class EmptyCommand: CliCommand {
    private val printWriter: PrintWriter by kodein.instance()

    override fun execute() {
        printWriter.println("I do nothing")
    }
}