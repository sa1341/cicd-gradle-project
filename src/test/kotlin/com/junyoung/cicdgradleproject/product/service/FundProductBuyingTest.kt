package com.junyoung.cicdgradleproject.product.service

import com.junyoung.cicdgradleproject.domain.product.meta.service.FundOutboundAdapter
import com.junyoung.cicdgradleproject.dto.FundBuyingReq
import com.junyoung.cicdgradleproject.dto.FundBuyingRes
import com.junyoung.cicdgradleproject.service.FundBuyingService
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@Tag("integrationTest")
@SpringBootTest
class FundProductBuyingTest {

    @Test
    fun `펀드 매수신청을 테스트한다`() {
        // given
        val mockAdapter = mockk<FundOutboundAdapter>()

        // when
        every { mockAdapter.buyFund(fundBuyingReq) } returns FundBuyingRes("FAIL!")

        val sut = FundBuyingService(mockAdapter)

        // then
        val res = sut.buyFund(fundBuyingReq)
        println("Response = $res")

        res?.status shouldBe "FAIL!"

        verify {
            mockAdapter.buyFund(fundBuyingReq)
        }
    }

    companion object {
        val fundBuyingReq = FundBuyingReq(
            accountNumber = "02000162758",
            fundCod = "2006001",
            name = "임준영",
            age = 32
        )
    }
}
