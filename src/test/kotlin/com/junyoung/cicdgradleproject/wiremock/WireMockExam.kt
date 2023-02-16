package com.junyoung.cicdgradleproject.wiremock

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.junyoung.cicdgradleproject.config.Phase
import com.junyoung.cicdgradleproject.config.WireMockBeanFactory
import com.junyoung.cicdgradleproject.config.WireMockContextInitializer
import com.junyoung.cicdgradleproject.util.WireMockUtils
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject

@ContextConfiguration(initializers = [WireMockContextInitializer::class])
@SpringBootTest
@ActiveProfiles("local")
class WireMockExam @Autowired constructor(
    @Value("\${wiremock.server.port}")
    private val mockPort: String,
    private val apiRestTemplate: RestTemplate,
    private val wireMockBeanFactory: WireMockBeanFactory,
    private val restTemplateObjectMapper: ObjectMapper,
    private val phase: Phase
) {
    private val url = "/test/wire-mock"
    private val wireMockServer = wireMockBeanFactory.fundWireMock()
    private val wireMockUtils = WireMockUtils(wireMockServer, url, restTemplateObjectMapper)

    @AfterEach
    fun afterEach() {
        wireMockServer.resetAll()
    }

    @Test
    fun `미리 정의된 stub를 사용한 wiremock 테스트`() {
        println("Phase Domain = ${phase.domain}")
        val responseBody = restTemplateObjectMapper.writeValueAsString(TestData("junyoung", 32))

        // given
        wireMockUtils.stubResponse(url, responseBody)

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
