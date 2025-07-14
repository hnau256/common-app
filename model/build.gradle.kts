import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
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
    linuxX64()

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
        publishLibraryVariants("release")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.hnau.kotlin)
                implementation(libs.arrow.core)
                implementation(libs.arrow.serialization)
                implementation(libs.kotlin.serialization.json)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.io)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.android.appcompat)
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