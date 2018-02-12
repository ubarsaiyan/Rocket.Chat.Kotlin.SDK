object Core {
    val libs = listOf(
            Libs.kotlin,
            Libs.coroutines,
            Libs.kotshiApi,
            Libs.KotshiCompiler,
            Libs.jsr305,
            Libs.json
    )

    val testLibs = listOf(
            TestLibs.kotlinJunit,
            TestLibs.junit,
            TestLibs.mockito,
            TestLibs.assertj,
            TestLibs.mockitoKotlin,
            TestLibs.mockWebServer
    )
}