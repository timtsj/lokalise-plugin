package com.timtsj.gradle

import org.gradle.api.Project

class LokalisePluginExtension {
    Collection<LokaliseProject> projects = new ArrayList<>()
    private Project project

    LokalisePluginExtension(Project project) {
        this.project = project
    }

    LokaliseProject generator(Closure closure) {
        def generator = this.project.configure(new LokaliseProject(), closure)
        projects.add(generator)
        return generator
    }
}