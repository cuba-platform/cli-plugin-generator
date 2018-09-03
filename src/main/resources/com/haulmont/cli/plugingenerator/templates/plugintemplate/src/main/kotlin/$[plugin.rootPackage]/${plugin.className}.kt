package ${plugin.rootPackage}

import com.google.common.eventbus.Subscribe
import com.haulmont.cuba.cli.CliPlugin
import com.haulmont.cuba.cli.event.InitPluginEvent

class ${plugin.className} : CliPlugin {
    @Subscribe
    fun onInit(event: InitPluginEvent) {
        event.commandsRegistry {
            command("empty", EmptyCommand())
        }
    }
}