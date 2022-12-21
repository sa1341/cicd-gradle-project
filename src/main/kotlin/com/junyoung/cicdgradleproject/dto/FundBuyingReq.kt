package com.junyoung.cicdgradleproject.dto

data class FundBuyingReq(
    val accountNumber: String,
    val fundCod: String,
    val name: String,
    val age: Int
)
