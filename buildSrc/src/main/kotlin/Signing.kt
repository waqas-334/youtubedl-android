package com.yausername.youtubedl_android

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugins.signing.SigningExtension

internal fun Project.configureSigning() {
    val publishing = extensions.getByType<PublishingExtension>()


    // Skip signing if building on JitPack
    if (System.getenv("JITPACK") != null) {
        println("🚫 Skipping signing on JitPack")
        return
    }

    println("🔐 Signing enabled (non-JitPack environment)")


    extensions.getByType<SigningExtension>().run {
        useGpgCmd()
        sign(publishing.publications["release"])
    }
}