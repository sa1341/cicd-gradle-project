package com.junyoung.cicdgradleproject.domain.entity.ad

data class AdEvent(
    val eventType: EventType,
    val eventSpec: EventSpec? = null,
    val iconType: IconType,
    val eventDescription: String,
    val eventUrl: String? = null,
    val landingType: LandingType? = null
)
