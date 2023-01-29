package com.junyoung.cicdgradleproject.mockk

import com.junyoung.cicdgradleproject.mockk.code.Adder
import com.junyoung.cicdgradleproject.mockk.code.Divider
import io.kotest.matchers.shouldBe
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import io.mockk.verifySequence
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

    @Test
    @DisplayName("mockk에서 제공하는 verify(검증) 테스트 코드를 작성한다.")
    fun verifyTest() {
        // given
        // given
        val mock1 = mockk<Mock>()
        val mock2 = mockk<Mock>()

        // when
        every { mock1.call(5) } returns 1
        every { mock1.call(6) } returns 2
        every { mock2.call(3) } returns 3

        // when
        val res1 = mock1.call(5)
        println("Response1 = $res1")

        val res2 = mock1.call(6)
        println("Response2 = $res2")

        // then
        verify {
            mock1.call(5)
            mock1.call(6)
        }

        verify {
            mock2 wasNot Called
        }

        // 정확한 호출 순서를 검증하기 위해서 사용함
        verifySequence {
            mock1.call(5)
            mock1.call(6)
        }
    }

    @Test
    fun capturingArguments() {
        // given
        val slot = slot<Int>()
        val mock = mockk<Divider>()

        every { mock.divide(capture(slot), any()) } answers { slot.captured * 11 }

        // when
        val res = mock.divide(5, 2)
        println("Response = $res")

        // then
        slot.captured shouldBe 5
        res shouldBe 55
    }

    @Test
    @DisplayName("Relaxed mockk 사용하여 예상되는 행위를 스킵하고 null or 0 같은 기본 값으로 응답하도록 테스트 한다.")
    fun relaxedMockkTest() {
        // given
        val mock = mockk<Divider>(relaxed = true)

        // when
        val res = mock.divide(5, 2)

        // then
        println("Response = $res")
        res shouldBe 0

        verify {
            mock.divide(5, 2)
        }
    }

    @Test
    @DisplayName("spies는 객체의 기존 메서드를 호출하는 동안 호출되어지는 다른 메서드도 동시에 행위를 검증하기 위해 제공됩니다.")
    fun spiesTest() {
        // given
        val spy = spyk<Adder>()

        // then
        spy.add(4, 5) shouldBe 9

        every { spy.magnify(any()) } answers { firstArg<Int>() * 2 }
        spy.add(4, 5) shouldBe 14

        verify { spy.add(4, 5) }
        verify { spy.magnify(5) }
    }
}

class Mock {
    fun call(value: Int): Int {
        return value
    }
}
