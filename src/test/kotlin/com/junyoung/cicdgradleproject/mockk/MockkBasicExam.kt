package com.junyoung.cicdgradleproject.mockk

import com.junyoung.cicdgradleproject.mockk.code.Adder
import com.junyoung.cicdgradleproject.mockk.code.Divider
import com.junyoung.cicdgradleproject.mockk.code.Order
import io.kotest.matchers.shouldBe
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.assertThrows
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
    @DisplayName("예외를 던지는 stub를 정의한다")
    fun `예외를 던지는 stub를 정의한다`() {
        // given
        val mock = mockk<Adder>()
        every { mock.add(any(), any()) } throws IllegalArgumentException("Args is not valid")

        // when
        val result = assertThrows(IllegalArgumentException::class.java) {
            mock.add(1, 2)
        }

        // then
        result.message shouldBe "Args is not valid"
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
    fun spyTest1() {
        // given
        val spy = spyk<Adder>()

        // then
        spy.add(4, 5) shouldBe 9

        every { spy.magnify(any()) } answers { firstArg<Int>() * 2 }
        spy.add(4, 5) shouldBe 14

        verify { spy.add(4, 5) }
        verify { spy.magnify(5) }
    }

    @Test
    @DisplayName("spy를 사용하면 테스트에 필요한 프로퍼티, 메서드만 stub해서 사용 할 수 있음")
    fun spyTest2() {
        // given
        val order = mockk<Order>() {
            every { orderAmount } returns 19_800
            every { deliveryFee } returns 2_500
        }

        // when
        // spy와 mock을 혼합해서 사용 가능함. 참고로 spy는 찐 객체를 생성함.
        val amountForPayment = spyk(order).getAmountForPayment()
        println("amountForPayment = $amountForPayment")

        // then
        assert(19_800 + 2_500 == amountForPayment)
    }

    @Test
    @DisplayName("mockObject 메서드는 싱글톤 객체를 mocking 합니다. 행위를 정의하지 않으면 실제 정의된 메서드를 호출합니다.")
    fun `싱글톤 객체를 mocking 한다`() {
        // given
        mockkObject(Calculator)

        // when
        println(Calculator.add(1, 3))

        // then
        verify {
            Calculator.add(1, 3)
        }
    }
}

object Calculator {
    fun add(a: Int, b: Int) = a + b
}

class Mock {
    fun call(value: Int): Int {
        return value
    }
}
