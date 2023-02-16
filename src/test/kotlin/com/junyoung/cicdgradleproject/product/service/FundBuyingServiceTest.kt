package com.junyoung.cicdgradleproject.product.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.junyoung.cicdgradleproject.config.WireMockBeanFactory
import com.junyoung.cicdgradleproject.config.WireMockContextInitializer
import com.junyoung.cicdgradleproject.util.WireMockUtils
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate

@ContextConfiguration(initializers = [WireMockContextInitializer::class])
@SpringBootTest
@ActiveProfiles("test")
class FundBuyingServiceTest @Autowired constructor(
    @Value("\${wiremock.server.port}")
    private val mockPort: String,
    private val apiRestTemplate: RestTemplate,
    private val wireMockBeanFactory: WireMockBeanFactory,
    private val restTemplateObjectMapper: ObjectMapper
) {

    private val url = "/test/wire-mock"
    private val wireMockServer = wireMockBeanFactory.fundWireMock()
    private val wireMockUtils = WireMockUtils(wireMockServer, url, restTemplateObjectMapper)

    @AfterEach
    fun afterEach() {
        wireMockServer.resetAll()
    }

/*    @Test
    fun `펀드 매수신청 서비스를 호출한다`() {
        val jsonStr = restTemplateObjectMapper.writeValueAsString(FundBuyingRes("SUCCESS"))
        wireMockUtils.stubResponse(url, jsonStr)

        val outboundAdapter = FundOutboundAdapter(apiRestTemplate)
        val sut = FundBuyingService(outboundAdapter)

        val res =
            sut.buyFund(FundBuyingReq(accountNumber = "02000162758", fundCod = "200605", name = "jeancalm", age = 32))

        res?.status shouldBe "SUCCESS"
    }*/
}
