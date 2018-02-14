object Versions {
    const val KOTLIN = "1.2.21"
    //PLUGINS
    const val KOTLINTER = "1.5.0"
    const val DOKKA = "0.9.15"
    const val GRADLE_VERSIONS_PLUGIN = "0.17.0"

    //LIBS
    const val COROUTINE = "0.22.2"
    const val KOTSHI = "1.0.0"
    const val OKHTTP = "3.9.1"
    const val MOSHI = "1.6.0-SNAPSHOT"
    const val JSR = "3.0.2"
    const val JSON = "20180130"

    //TEST
    const val JUNIT = "4.12"
    const val MOCKITO = "2.15.0"
    const val ASSERTJ = "3.9.0"
    const val MOCKITO_KOTLIN = "1.5.0"
    const val MOCKWEBSERVER = "852ce0b657"
}

private object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE}"
    const val KOTSHI = "se.ansman.kotshi:api:${Versions.KOTSHI}"
    const val KOTSHI_COMPILER = "se.ansman.kotshi:compiler:${Versions.KOTSHI}"
    const val JSR305 = "com.google.code.findbugs:jsr305:${Versions.JSR}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val JSON = "org.json:json:${Versions.JSON}"
}

internal object Libs {
    val kotlin = Compile(Dependencies.KOTLIN)
    val coroutines = Compile(Dependencies.COROUTINES)
    val kotshiApi = Compile(Dependencies.KOTSHI)
    val KotshiCompiler = Kapt(Dependencies.KOTSHI_COMPILER)
    val jsr305 = CompileOnly(Dependencies.JSR305)
    val okhttp = Compile(Dependencies.OKHTTP)
    val moshi = Compile(Dependencies.MOSHI)
    val json = CompileOnly(Dependencies.JSON)
}

private object TestDependencies {
    const val KOTLIN_JUNIT = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.KOTLIN}"
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val MOCKITO = "org.mockito:mockito-core:${Versions.MOCKITO}"
    const val ASSERTJ = "org.assertj:assertj-core:${Versions.ASSERTJ}"
    const val MOCKITO_KOTLIN = "com.nhaarman:mockito-kotlin-kt1.1:${Versions.MOCKITO_KOTLIN}"
    const val MOCKWEBSERVER = "com.github.luciofm:mockwebserver:${Versions.MOCKWEBSERVER}"
}

internal object TestLibs {
    val kotlinJunit = TestCompile(TestDependencies.KOTLIN_JUNIT)
    val junit = TestCompile(TestDependencies.JUNIT)
    val mockito = TestCompile(TestDependencies.MOCKITO)
    val assertj = TestCompile(TestDependencies.ASSERTJ)
    val mockitoKotlin = TestCompile(TestDependencies.MOCKITO_KOTLIN)
    val mockWebServer = TestCompile(TestDependencies.MOCKWEBSERVER)
}

private const val CLASSPATH = "classpath"
private const val COMPILE = "compile"
private const val COMPILE_ONLY = "compileOnly"
private const val KAPT = "kapt"
private const val TEST_COMPILE = "testCompile"

sealed class Dependency(val configuration: String, val dependency: String)
class Classpath(dependency: String) : Dependency(CLASSPATH, dependency)
class Compile(dependency: String) : Dependency(COMPILE, dependency)
class Kapt(dependency: String) : Dependency(KAPT, dependency)
class CompileOnly(dependency: String) : Dependency(COMPILE_ONLY, dependency)
class TestCompile(dependency: String) : Dependency(TEST_COMPILE, dependency)