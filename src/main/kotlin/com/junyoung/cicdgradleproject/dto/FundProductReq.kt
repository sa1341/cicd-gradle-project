package com.junyoung.cicdgradleproject.dto

import com.junyoung.cicdgradleproject.const.FundType

data class FundProductReq(
    val fundCode: String,
    val name: String,
    val type: FundType
)
