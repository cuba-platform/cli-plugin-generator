import com.haulmont.cli.plugingenerator.PluginGeneratorPlugin;
import com.haulmont.cuba.cli.CliPlugin;

module com.haulmont.cuba.cli.plugingenerator {
    requires kotlin.stdlib.jdk8;
    requires kotlin.stdlib.jdk7;
    requires kotlin.stdlib;
    requires kotlin.reflect;

    requires com.haulmont.cuba.cli;
    requires com.google.common;

    requires jdk.zipfs;
    requires kodein.di.core.jvm;
    requires kodein.di.generic.jvm;

    requires jcommander;

    exports com.haulmont.cli.plugingenerator;
    opens com.haulmont.cli.plugingenerator;

    provides CliPlugin with PluginGeneratorPlugin;
}