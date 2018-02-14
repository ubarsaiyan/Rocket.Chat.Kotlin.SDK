internal object ClasspathDependencies {
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val KOTLINTER = "gradle.plugin.org.jmailen.gradle:kotlinter-gradle:${Versions.KOTLINTER}"
    const val DOKKA = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.DOKKA}"
    const val GRADLE_VERSIONS_PLUGIN = "com.github.ben-manes:gradle-versions-plugin:${Versions.GRADLE_VERSIONS_PLUGIN}"
}

internal object ClasspathLibs {
    val kotlinGradlePlugin = Classpath(ClasspathDependencies.KOTLIN_GRADLE_PLUGIN)
    val kotlinter = Classpath(ClasspathDependencies.KOTLINTER)
    val dokka = Classpath(ClasspathDependencies.DOKKA)
    val gradleVersionsPlugin = Classpath(ClasspathDependencies.GRADLE_VERSIONS_PLUGIN)
}

object Global {
    val classpath = listOf(
            ClasspathLibs.kotlinGradlePlugin,
            ClasspathLibs.kotlinter,
            ClasspathLibs.dokka,
            ClasspathLibs.gradleVersionsPlugin
    )
}