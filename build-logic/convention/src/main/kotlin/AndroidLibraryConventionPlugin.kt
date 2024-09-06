import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                // Android Library Module 都需要这 2 个插件
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("wim.android.lint")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                defaultConfig.targetSdk = 34
                // The resource prefix is derived from the module name,
                // so resources inside ":core:module1" must be prefixed with "core_module1_"
                resourcePrefix = project.name.lowercase() + "_"
                namespace = "com.wim"  + "." + project.name.lowercase()
            }
        }
    }
}