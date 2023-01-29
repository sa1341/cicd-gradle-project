package com.junyoung.cicdgradleproject.domain.product.meta.ad

import java.math.BigDecimal

enum class EventType(private val eventSpecs: List<EventSpec>) {
    PRODUCT_DETAIL(listOf()),
    MY_FUND(listOf(EventSpec.REPORT));

    fun getExceptEventSpecs(value: BigDecimal): List<EventSpec> {
        return eventSpecs.filter { !it.exposure(value) }
    }
}

enum class EventSpec {
    NONE,
    REPORT {
        override fun exposure(value: BigDecimal): Boolean = value > BigDecimal.ZERO
    };

    open fun exposure(value: BigDecimal): Boolean = true
}
