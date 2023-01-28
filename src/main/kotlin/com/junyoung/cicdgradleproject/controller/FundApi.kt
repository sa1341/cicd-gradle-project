package com.junyoung.cicdgradleproject.controller

import com.junyoung.cicdgradleproject.domain.entity.FundProductEntity
import com.junyoung.cicdgradleproject.service.FundProductService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

   /* @PostMapping("/funds/create-fund")
    fun saveFundProduct(@RequestBody fundProductReq: FundProductReq): ResponseEntity<String> {
        fundProductService.saveFundProduct(fundProductReq)
        return ResponseEntity.ok("SUCCESS")
    }*/
}
