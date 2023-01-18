package com.junyoung.cicdgradleproject.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.junyoung.cicdgradleproject.domain.entity.FundProductEntity
import com.junyoung.cicdgradleproject.dto.FundBuyingDto
import com.junyoung.cicdgradleproject.dto.FundBuyingReq
import com.junyoung.cicdgradleproject.dto.FundBuyingRes
import com.junyoung.cicdgradleproject.dto.FundProductReq
import com.junyoung.cicdgradleproject.service.FundProductService
import mu.KotlinLogging
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.core5.http.ContentType
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.apache.hc.core5.http.io.entity.StringEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

private val logger = KotlinLogging.logger {}

@RequestMapping("/api/v1")
@RestController
class FundApi(
    private val fundProductService: FundProductService
) {

    @GetMapping("/funds/{fundCode}")
    fun getFundProduct(@PathVariable(required = true) fundCode: String): FundProductEntity? {
        return fundProductService.getFundProduct(fundCode = fundCode)
    }

    @PostMapping("/funds/create-fund")
    fun saveFundProduct(@RequestBody fundProductReq: FundProductReq): ResponseEntity<String> {
        fundProductService.saveFundProduct(fundProductReq)
        return ResponseEntity.ok("SUCCESS")
    }
}
