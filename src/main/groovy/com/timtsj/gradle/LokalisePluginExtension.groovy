package com.timtsj.gradle

import org.gradle.api.Project

class LokalisePluginExtension {
    Collection<LokaliseProject> projects = new ArrayList<>()
    private Project project

    LokalisePluginExtension(Project project) {
        this.project = project
    }

    LokaliseProject proj(Closure closure) {
        def project = this.project.configure(new LokaliseProject(), closure)
        projects.add(project)
        return project
    }
}