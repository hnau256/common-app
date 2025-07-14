import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeDesktop)
    id("maven-publish")
}

android {
    namespace = "com.github.hnau256." + project.name.replace('-', '.')
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
    }
    compileOptions {
        val javaVersion = libs.versions.java.get().let(JavaVersion::valueOf)
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    jvm()

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
        publishLibraryVariants("release")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":model"))
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(libs.hnau.kotlin)
                implementation(libs.hnau.dynamiccolor)
                implementation(libs.arrow.core)
                implementation(libs.kotlin.serialization.json)
                implementation(libs.kotlin.immutable)
                implementation(libs.kotlin.coroutines.core)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.android.appcompat)
                implementation(libs.android.activityCompose)
            }
        }
    }
}

publishing {
    publications {
        configureEach {
            (this as MavenPublication).apply {
                groupId = project.group as String
                version = project.version as String
            }
        }
    }
}