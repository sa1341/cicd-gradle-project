package com.junyoung.cicdgradleproject.domain.product.meta.service

import com.junyoung.cicdgradleproject.dto.FundBuyingReq
import com.junyoung.cicdgradleproject.dto.FundBuyingRes
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class FundOutboundAdapter(
    private val apiRestTemplate: RestTemplate
) {

    fun buyFund(fundBuyingReq: FundBuyingReq): FundBuyingRes? {
        return apiRestTemplate.postForObject(
            "http://localhost:8082/api/v1/test-post",
            fundBuyingReq,
            FundBuyingRes::class.java
        ) ?: throw RuntimeException("펀드 호출 실패")
    }
}
