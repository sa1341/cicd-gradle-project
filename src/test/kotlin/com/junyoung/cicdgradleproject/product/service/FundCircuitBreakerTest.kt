package com.junyoung.cicdgradleproject.product.service

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

const val CIRCUIT_BREAKER_INSTANCE = "money"

@Tag("unit")
@SpringBootTest
class FundCircuitBreakerTest @Autowired private constructor(
    private val circuitBreakerRegistry: CircuitBreakerRegistry
) {

    @Test
    fun `서킷브레이커 인스턴스명을 조회한다`() {
        val circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_INSTANCE)
        circuitBreaker.transitionToOpenState()
        circuitBreaker.state shouldBe CircuitBreaker.State.OPEN
    }
}
