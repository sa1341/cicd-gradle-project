package com.junyoung.cicdgradleproject.mockk

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockkBasicExam {

    @Test
    @DisplayName("mockk가 제공하는 아규먼트 매칭 기능을 테스트한다.")
    fun argumentMatchingTest() {
        // given
        val mock1 = mockk<Mock>()

        // when
        every { mock1.call(more(5)) } returns 1

        // then
        val res1 = mock1.call(6)
        res1 shouldBe 1

        every { mock1.call(or(less(5), eq(5))) } returns -1
        val res2 = mock1.call(3)
        res2 shouldBe -1
    }
}

class Mock {
    fun call(value: Int): Int {
        return value
    }
}
