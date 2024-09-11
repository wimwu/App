import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.wim.plugin.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    // register the convention plugin
    plugins {
        register("androidApplication") {
            id = "wim.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "wim.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "wim.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidLint") {
            id = "wim.android.lint"
            implementationClass = "AndroidLintConventionPlugin"
        }
        register("androidHilt") {
            id = "wim.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }
    }
}