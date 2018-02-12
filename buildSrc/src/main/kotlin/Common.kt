object Common {
    val libs = listOf(
            Libs.kotlin,
            Libs.kotshiApi,
            Libs.KotshiCompiler,
            Libs.jsr305,
            Libs.okhttp,
            Libs.moshi
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