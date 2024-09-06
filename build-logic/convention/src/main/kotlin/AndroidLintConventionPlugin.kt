import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.Lint
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            when {
                pluginManager.hasPlugin("com.android.application") ->
                    configure<ApplicationExtension> { lint(Lint::configure) }

                pluginManager.hasPlugin("com.android.library") ->
                    configure<LibraryExtension> { lint(Lint::configure) }

                else -> {
                    pluginManager.apply("com.android.lint")
                    configure<Lint>(Lint::configure)
                }
            }
        }
    }
}

private fun Lint.configure() {
    // 此行告诉 Lint 即使在代码分析期间发现错误也不要停止构建过程。
    abortOnError = false
    // 此行启用为 lint 结果生成 XML 报告。XML 报告提供了一种结构化且机器可读的格式来分析 lint 问题。
    xmlReport = true
    // 此行使 lint 能够检查项目的依赖项。这意味着 lint 将分析您的项目使用的库和模块，并识别潜在的问题， 例如版本冲突、 过时的库或未使用的依赖项。
    checkDependencies = true
}