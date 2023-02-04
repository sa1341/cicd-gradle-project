package com.junyoung.cicdgradleproject.controller

import com.junyoung.cicdgradleproject.domain.product.FundProductEntity
import com.junyoung.cicdgradleproject.dto.DateType
import com.junyoung.cicdgradleproject.dto.FundBuyingReq
import com.junyoung.cicdgradleproject.dto.FundBuyingRes
import com.junyoung.cicdgradleproject.dto.FundProductReq
import com.junyoung.cicdgradleproject.service.FundBuyingService
import com.junyoung.cicdgradleproject.service.FundProductService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

@RequestMapping("/api/v1")
@RestController
class FundApi(
    private val fundProductService: FundProductService,
    private val fundBuyingService: FundBuyingService
) {

    @GetMapping("/funds/{fundCode}")
    fun getFundProduct(@PathVariable(required = true) fundCode: String): FundProductEntity? {
        logger.info { "fundCode = $fundCode" }
        return fundProductService.getFundProduct(fundCode = fundCode)
    }

    @PostMapping("/funds/create-fund")
    fun saveFundProduct(@RequestBody fundProductReq: FundProductReq): ResponseEntity<String> {
        fundProductService.saveFundProduct(fundProductReq)
        return ResponseEntity.ok("SUCCESS")
    }

    @GetMapping("/date-type")
    fun sendDateType(): DateType {
        return DateType(LocalDate.now(), LocalDateTime.now())
    }

    @PostMapping("/get-date-type")
    fun getDateType(@RequestBody dateType: DateType): DateType {
        logger.info { "DateType = $dateType" }
        return DateType(LocalDate.now(), null)
    }

    @PostMapping("/buying-fund")
    fun buyFund(@RequestBody fundBuyingReq: FundBuyingReq): FundBuyingRes? {
        return fundBuyingService.buyFund(fundBuyingReq)
    }
}
