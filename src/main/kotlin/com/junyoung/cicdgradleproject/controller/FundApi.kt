package com.junyoung.cicdgradleproject.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.junyoung.cicdgradleproject.dto.FundBuyingDto
import com.junyoung.cicdgradleproject.dto.FundBuyingReq
import com.junyoung.cicdgradleproject.dto.FundBuyingRes
import mu.KotlinLogging
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.core5.http.ContentType
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.apache.hc.core5.http.io.entity.StringEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

private val logger = KotlinLogging.logger {}

@RequestMapping("/api/v1")
@RestController
class FundApi(
    private val httpClient: CloseableHttpClient,
    private val mapper: ObjectMapper
) {

    @Value("\${fund.service.url}")
    lateinit var serviceUrl: String

    @PostMapping("/fund/buying-fund")
    fun buyFund(@RequestBody fundBuyingDto: FundBuyingDto): ResponseEntity<FundBuyingRes> {

        logger.debug { "Service Url: $serviceUrl" }

        val fundBuyingReq = FundBuyingReq(
            accountNumber = fundBuyingDto.accountNumber, fundCod = fundBuyingDto.fundCod, name = "임준영", 32
        )

        val jsonBody = mapper.writeValueAsString(fundBuyingReq)
        logger.debug { "JSON BODY = $jsonBody" }

        val path = "/api/v1/funds/buying-fund"
        val httpPost = HttpPost(serviceUrl + path)
        httpPost.entity = StringEntity(jsonBody, ContentType.APPLICATION_JSON)

        try {
            val response = httpClient.execute(httpPost)
            val result = EntityUtils.toString(response.entity)
            val fundBuyingRes = mapper.readValue(result, FundBuyingRes::class.java)
            logger.debug { "FundBuyingRes: $fundBuyingRes" }
            return ResponseEntity.ok().body(fundBuyingRes)
        } catch (ie: IOException) {
            logger.error { "ERROR: $ie" }
            throw ie
        }
    }
}
