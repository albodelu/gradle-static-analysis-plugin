package com.novoda.staticanalysis.internal

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class CollectViolationsTask extends DefaultTask {

    private File xmlReportFile
    private Violations violations

    CollectViolationsTask() {
        onlyIf { xmlReportFile?.exists() }
    }

    void setXmlReportFile(File xmlReportFile) {
        this.xmlReportFile = xmlReportFile
    }

    void setViolations(Violations violations) {
        this.violations = violations
    }

    @TaskAction
    final void run() {
        collectViolations(xmlReportFile, htmlReportFile, violations)
    }

    File getXmlReportFile() {
        return xmlReportFile
    }

    File getHtmlReportFile() {
        new File(xmlReportFile.absolutePath - '.xml' + '.html')
    }

    abstract void collectViolations(File xmlReportFile, File htmlReportFile, Violations violations)
}
