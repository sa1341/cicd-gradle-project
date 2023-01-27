package com.junyoung.cicdgradleproject.repository

import com.junyoung.cicdgradleproject.data.fundProductEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles("test")
@SpringBootTest
class FundProductRepositoryTest {

    @Autowired
    lateinit var fundProductRepository: FundProductRepository

    @BeforeEach
    fun setUp() {
        val savedFundProduct = fundProductRepository.save(fundProductEntity)
        println("SavedFundProduct = $savedFundProduct")
    }

    @Transactional(readOnly = true)
    @Test
    fun getFundProduct() {
        // given
        val fundCode = "260005"

        // when
        val findFundProduct = fundProductRepository.findByFundCode(fundCode)

        // then
        findFundProduct?.productName shouldBe "키움똑똑이"
    }
}
