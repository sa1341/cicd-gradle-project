package com.junyoung.cicdgradleproject.controller

import com.junyoung.cicdgradleproject.dto.AgitPostReq
import com.junyoung.cicdgradleproject.dto.AgitPostRes
import com.junyoung.cicdgradleproject.dto.FundBuyingReq
import com.junyoung.cicdgradleproject.service.AgitWritingService
import mu.KotlinLogging
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}

@RequestMapping("/api/agit")
@RestController
class AgitApi(
    private val agitWritingService: AgitWritingService
) {

    @PostMapping("/article")
    fun sendPost(@RequestBody agitPostReq: AgitPostReq): AgitPostRes {
        logger.debug { "AgitPostRequest = $agitPostReq" }
        return agitWritingService.writeDailyPost(agitPostReq = agitPostReq)
    }
}
