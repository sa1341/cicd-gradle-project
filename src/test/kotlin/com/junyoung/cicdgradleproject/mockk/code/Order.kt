package com.junyoung.cicdgradleproject.mockk.code

data class Order(
    val orderNo: Long,
    val orderAmount: Int,
    val deliveryFee: Int,
    val name: String,
    val address: String,
    val contact: String
) {
    fun getAmountForPayment() = orderAmount + deliveryFee
}
