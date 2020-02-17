package com.timtsj.gradle

import com.timtsj.gradle.tasks.DownloadStrings
import com.timtsj.gradle.tasks.RefreshStrings
import com.timtsj.gradle.tasks.UploadStrings
import org.gradle.api.Plugin
import org.gradle.api.Project

class LokalisePlugin implements Plugin<Project> {
    @Override void apply(Project target) {
        target.extensions.add("lokalise", new LokalisePluginExtension(target))

        target.afterEvaluate {
            def android = target.extensions.getByName("android")
            if (!android) {
                throw new RuntimeException("Not an Android application; did you forget `apply plugin: 'com.android.application`?")
            }

            def extension = target.extensions.findByName("lokalise") as LokalisePluginExtension

            LokaliseExtensionDataChecker.validateExtensionDataIsCorrect(extension, target.name)

            def lokaliseUpload = target.tasks.create("uploadStrings", UploadStrings) {
                lokalise_token = extension.token
                lokalise_id = extension.id
                project = target
            }
            lokaliseUpload.group = "lokalise"
            lokaliseUpload.description = "Upload localise files"

            def lokaliseDownload = target.tasks.create("downloadStrings", DownloadStrings) {
                extension.projects.each {
                    lokalise_token = it.token
                    lokalise_id = it.id
                    file_name = it.fileName
                    project = target
                }
            }
            lokaliseDownload.group = "lokalise"
            lokaliseDownload.description = "Download localise files"

            def lokaliseRefresh = target.tasks.create("refreshStrings", RefreshStrings) {
                uploadTask = lokaliseUpload
                downloadTask = lokaliseDownload
            }
            lokaliseRefresh.group = "lokalise"
            lokaliseRefresh.description = "Update localise files"
        }
    }
}