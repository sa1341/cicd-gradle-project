package com.junyoung.cicdgradleproject.repository

import com.junyoung.cicdgradleproject.const.FundType
import com.junyoung.cicdgradleproject.domain.entity.FundProductEntity
import com.junyoung.cicdgradleproject.domain.repository.FundProductJpaRepository
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class FundProductRepositoryTest {

    val fundCode = "260005"

    @Rollback(value = false)
    @Transactional
    @Test
    fun saveFundProduct() {
        val repository = mockk<FundProductRepository>()

        // given
        every { repository.save(any()) } returns FundProductEntity(code = "260005", name = "한화쏠쏠이", type = FundType.STOCK)

        // when
        val savedFundProduct = repository.save(FundProductEntity(code = "260005", name = "한화쏠쏠이", type = FundType.STOCK))

        // then
        savedFundProduct.code shouldBe fundCode
    }

    @Transactional(readOnly = true)
    @Test
    fun getFundProduct() {
        // given
        val repository = mockk<FundProductRepository>()

        // given
        every { repository.findByIdOrNull(any()) } returns FundProductEntity(code = "260005", name = "키움똑똑이", type = FundType.STOCK)

        // when
        val findFundProduct = repository.findByIdOrNull("Dummy")

        // then
        findFundProduct?.name shouldBe "키움똑똑이"
    }
}
