package com.junyoung.cicdgradleproject.controller

import com.junyoung.cicdgradleproject.domain.entity.FundProductEntity
import com.junyoung.cicdgradleproject.dto.FundProductReq
import com.junyoung.cicdgradleproject.service.FundProductService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
