package com.junyoung.cicdgradleproject.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Configuration
class PhaseConfig(
    private val environment: Environment
) {
    @Bean
    fun phase() = Phase[environment.activeProfiles]
}

enum class Phase(
    var domain: String
) {
    TEST("http://localhost:8090/"),
    LOCAL("https://qa-nex-std-api.kakaopayinvest.com/"),
    DEV("https://qa-nex-std-api.kakaopayinvest.com/"),
    SANDBOX("https://qa-nex-std-api.kakaopayinvest.com/"),
    BETA("https://beta-nex-std-api.kakaopayinvest.com/"),
    PRODUCTION("https://nex-std-api.kakaopayinvest.com/");

    @Profile("test")
    @Component
    class WireMockHostInjector(
        @Value("\${wiremock.server.port}") private val port: String
    ) {
        init {
            TEST.domain = "http://localhost:$port/"
            println("WireMockHostInjector initialized with port $port")
        }
    }

    companion object {
        operator fun get(profiles: Array<String>): Phase =
            profiles.map { it.uppercase() }.run {
                when {
                    contains(PRODUCTION.name) -> PRODUCTION
                    contains(BETA.name) -> BETA
                    contains(SANDBOX.name) -> SANDBOX
                    contains(DEV.name) -> DEV
                    contains(TEST.name) -> TEST
                    else -> LOCAL
                }
            }
    }
}
