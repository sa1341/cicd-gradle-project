package com.junyoung.cicdgradleproject.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.apache.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class WireMockUtils(
    private val wireMockServer: WireMockServer,
    private val baseUrl: String,
    private val objectMapper: ObjectMapper
) {
    fun stubResponse(mockUrl: String, responseBody: String, responseStatus: Int = HttpStatus.OK.value()) {
        wireMockServer.stubFor(
            WireMock.any(WireMock.urlPathEqualTo(mockUrl))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(responseStatus)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(responseBody)
                )
        )
    }
}
