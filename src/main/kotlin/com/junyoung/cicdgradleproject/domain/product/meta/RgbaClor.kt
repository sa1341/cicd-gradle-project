package com.junyoung.cicdgradleproject.domain.product.meta

data class RgbaClor(
    val r: Int,
    val g: Int,
    val b: Int,
    val a: Float,
) {
    val code: String
        get() = "$r, $g, $b, $a"

    companion object {
        private val DEFAULT_RGBA_COLOR = RgbaClor(255, 255, 255, 0.9f)
        private const val DEFAULT_RGBA_FORMAT = "rgba(135, 146, 156, 0.9)"

        fun of(rgbaColor: String?): RgbaClor {
            return rgbaColor?.let {
                val split = rgbaColor.split(",")
                RgbaClor(
                    split[0].trim().toInt(),
                    split[1].trim().toInt(),
                    split[2].trim().toInt(),
                    split[3].trim().toFloat()
                )
            } ?: DEFAULT_RGBA_COLOR
        }

        fun formatting(rgbaColor: String?): String {
            return rgbaColor?.let { "rgba($rgbaColor)" } ?: DEFAULT_RGBA_FORMAT
        }
    }
}
