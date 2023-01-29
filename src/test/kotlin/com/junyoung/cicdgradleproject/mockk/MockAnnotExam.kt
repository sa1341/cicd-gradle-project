package com.junyoung.cicdgradleproject.mockk

import com.junyoung.cicdgradleproject.mockk.code.Divider
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MockAnnotExam {

    @MockK
    lateinit var divider: Divider

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun annotationMockkTest() {
        // given
        every { divider.divide(4, 2) } returns 1

        // when
        divider.divide(4, 2) shouldBe 1

        // then
        verify {
            divider.divide(4, 2)
        }
    }
}
