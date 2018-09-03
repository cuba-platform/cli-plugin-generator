package com.haulmont.cli.plugingenerator

import com.haulmont.cuba.cli.prompting.Answers

class PluginModel(answers: Answers) {
    val name: String by answers
    val rootPackage: String by answers
    val group: String by answers
    val className: String by answers
    val moduleName: String by answers
}
