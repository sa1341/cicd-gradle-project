package com.junyoung.cicdgradleproject.controller

import com.junyoung.cicdgradleproject.const.FundType
import com.junyoung.cicdgradleproject.domain.product.FundProduct
import com.junyoung.cicdgradleproject.dto.FundBuyingReq
import com.junyoung.cicdgradleproject.service.RedisFundService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RequestMapping("/api/redis")
@RestController
class RedisTestApi(
    private val redisFundService: RedisFundService
) {

    @PostMapping("/set-string")
    fun putString(): ResponseEntity<String> {
        val fundProduct = FundProduct("421224", "카카오증권채권펀드", FundType.BOND)
        val fundProducts = mutableListOf<FundProduct>()
        fundProducts.add(fundProduct)
        redisFundService.saveAllFundProduct(fundProducts)

        return ResponseEntity.ok().body("success")
    }

    @GetMapping("/fund-product/{code}")
    fun getFundProduct(@PathVariable code: String): ResponseEntity<String> {
        logger.debug { "FundCode: $code" }
        val fundProduct = redisFundService.getFundProduct(code)
        logger.debug { "FUndProduct = $fundProduct" }
        return ResponseEntity.ok().body("success")
    }

    @GetMapping("/fund-product")
    fun getAllFundProduct(): ResponseEntity<String> {
        redisFundService.getAllFundProduct().forEach {
            logger.debug { "FundProduct Name: ${it.name}" }
        }
        return ResponseEntity.ok().body("success")
    }

    @GetMapping("/save-redis-object")
    fun saveRedisObject(): String {
        val fundBuyingReq = FundBuyingReq(
            accountNumber = "02000162758",
            fundCod = "200601",
            name = "jean.calm",
            age = 31
        )

        redisFundService.saveRedisObject(fundBuyingReq)
        return "Success"
    }

    @GetMapping("/get-redis-object")
    fun getRedisObject(): FundBuyingReq? {
        val (accountNumber, fundCod) = "02000162758" to "200601"
        return redisFundService.getRedisObject(accountNumber, fundCod)
    }
}
