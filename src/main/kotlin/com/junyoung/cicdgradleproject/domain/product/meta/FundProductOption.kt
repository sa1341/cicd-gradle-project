package com.junyoung.cicdgradleproject.domain.product.meta

enum class FundProductOption(val optionName: String) {
    TERM("자동투자"),
    CHANGE("동전 모으기"),
    EGG_REWARD("알 모으기"),
    NEW("신규"),
    HIGH_RISK("고난도금융투자상품"),
    PROMOTION("프로모션");

    fun isJarvisProduct() = this == TERM || this == CHANGE || this == EGG_REWARD

    fun isNewProduct() = this == NEW

    companion object {
        fun getOptionList(): List<FundProductOption> {
            return values().toList()
        }
    }
}
