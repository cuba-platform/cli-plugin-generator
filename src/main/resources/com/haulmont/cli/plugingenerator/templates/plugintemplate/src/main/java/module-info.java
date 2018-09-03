import ${plugin.rootPackage}.${plugin.className};
import com.haulmont.cuba.cli.CliPlugin;

module ${plugin.moduleName} {
    requires com.haulmont.cuba.cli;
    requires com.google.common;

    requires kodein.di.core.jvm;
    requires kodein.di.generic.jvm;
    requires kotlin.stdlib;
    requires jcommander;

    exports ${plugin.rootPackage};
    opens ${plugin.rootPackage};

    provides CliPlugin with ${plugin.className};
}