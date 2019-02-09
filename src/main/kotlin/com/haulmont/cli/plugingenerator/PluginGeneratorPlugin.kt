package com.haulmont.cli.plugingenerator

import com.google.common.eventbus.Subscribe
import com.haulmont.cuba.cli.CliPlugin
import com.haulmont.cuba.cli.HasResources
import com.haulmont.cuba.cli.ResourcesPath
import com.haulmont.cuba.cli.event.InitPluginEvent

class PluginGeneratorPlugin : CliPlugin {
    override val apiVersion: Int
        get() = 3

    override val resources: ResourcesPath = HasResources("/com/haulmont/cli/plugingenerator/")

    @Subscribe
    fun onInit(event: InitPluginEvent) {
        event.commandsRegistry {
            command("create-plugin", PluginGeneratorCommand())
        }
    }
}