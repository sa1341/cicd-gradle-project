package com.junyoung.cicdgradleproject.domain.product.meta.ad

data class AdGuide(
    val lottieUrl: String,
    val darkLottieUrl: String? = null,
    val popupImageUrl: String? = null,
    val darkPopupImageUrl: String? = null,
) {
    companion object {
        fun from(guide: MetaFundProductAdGuideEntity): AdGuide {
            return AdGuide(
                lottieUrl = guide.adGuide.lottieUrl,
                darkLottieUrl = guide.adGuide.darkLottieUrl,
                popupImageUrl = guide.adGuide.popupImageUrl,
                darkPopupImageUrl = guide.adGuide.darkPopupImageUrl
            )
        }
    }
}
