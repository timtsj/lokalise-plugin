package com.timtsj.gradle

class LokaliseExtensionDataChecker {
    static def validateExtensionDataIsCorrect(LokalisePluginExtension extension, String projectName) {

        if (!extension) {
            throw new RuntimeException("Missing 'lokalise' closure in $projectName/build.gradle.")
        }

//        extension.projects.each {
//            if (!it.id && !it.token) {
//                throw new RuntimeException("Missing 'id' and 'token' property in 'lokalise' closure in $projectName/build.gradle.")
//            }
//
//            if (!it.id) {
//                throw new RuntimeException("Missing 'id' property in 'lokalise' closure in $projectName/build.gradle.")
//            }
//
//            if (!it.token) {
//                throw new RuntimeException("Missing 'token' property in 'lokalise' closure in $projectName/build.gradle.")
//            }
//        }
    }
}