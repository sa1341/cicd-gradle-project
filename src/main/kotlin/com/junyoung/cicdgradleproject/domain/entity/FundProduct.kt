package com.junyoung.cicdgradleproject.domain.entity

import com.junyoung.cicdgradleproject.const.FundType
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "fund-product")
class FundProduct(
    @Id
    val code: String,
    val name: String,
    val type: FundType
)
