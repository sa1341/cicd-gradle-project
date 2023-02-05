package com.junyoung.cicdgradleproject.service

import com.junyoung.cicdgradleproject.domain.product.meta.service.FundOutboundAdapter
import com.junyoung.cicdgradleproject.dto.FundBuyingReq
import com.junyoung.cicdgradleproject.dto.FundBuyingRes
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class FundBuyingService(
    private val fundOutboundAdapter: FundOutboundAdapter
) {
    fun buyFund(fundBuyingReq: FundBuyingReq): FundBuyingRes? {
        val res = fundOutboundAdapter.buyFund(fundBuyingReq)
        logger.info { "Response = $res" }
        return res
    }
}
