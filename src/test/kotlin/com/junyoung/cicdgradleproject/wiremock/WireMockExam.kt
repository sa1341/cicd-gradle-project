package com.junyoung.cicdgradleproject.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.apache.http.HttpHeaders
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject

@ContextConfiguration(initializers = [WireMockContextInitializer::class])
@SpringBootTest
@ActiveProfiles("test")
class WireMockExam {

    @Value("\${wiremock.server.port}")
    lateinit var mockPort: String

    @Autowired
    lateinit var wireMockServer: WireMockServer

    @Autowired
    lateinit var apiRestTemplate: RestTemplate

    @AfterEach
    fun afterEach() {
        wireMockServer.resetAll()
    }

    // stub 정의 메서드
    private fun stubResponse(mockUrl: String, responseBody: String, responseStatus: Int = HttpStatus.OK.value()) {
        wireMockServer.stubFor(
            WireMock.any(WireMock.urlPathEqualTo(mockUrl))
                .willReturn(
                    aResponse()
                        .withStatus(responseStatus)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(responseBody)
                )
        )
    }

    @Test
    fun `미리 정의된 stub를 사용한 wiremock 테스트`() {
        // given
        stubResponse(url, responseBody)

        // when
        val res = apiRestTemplate.postForObject<TestData>(
            url = "http://localhost:$mockPort/test/wire-mock"
        )

        println("Response = $res")

        // then
        wireMockServer.verify(postRequestedFor(urlEqualTo(url)))
    }

    @Test
    fun `json 파일에 정의된 stub를 사용한 wiremock 테스트`() {

        // given
        val getUrl = "/stub/wire-mock"
        println("SIZE = ${wireMockServer.stubMappings.size}")

        // when
        val res = apiRestTemplate.getForObject(
            "http://localhost:$mockPort$getUrl",
            TestData::class.java
        )

        println("Response = $res")

        // then
        wireMockServer.verify(getRequestedFor(urlEqualTo(getUrl)))
    }

    companion object {
        const val url = "/test/wire-mock"
        val responseBody = """
         {
           "name": "임준영",
           "age": 32
         }
    """.trimIndent()
    }

    private data class TestData(
        val name: String,
        val age: Int
    )
}
