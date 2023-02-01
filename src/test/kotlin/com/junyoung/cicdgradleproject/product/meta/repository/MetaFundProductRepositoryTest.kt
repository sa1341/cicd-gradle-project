package com.junyoung.cicdgradleproject.product.meta.repository

import com.junyoung.cicdgradleproject.domain.product.meta.repository.MetaFundProductRepository
import com.junyoung.cicdgradleproject.product.data.metaFundProductEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("local")
class MetaFundProductRepositoryTest {

    @Autowired
    lateinit var metaFundProductRepository: MetaFundProductRepository

    @BeforeEach
    fun setUp() {
        println("JUnit 테스트 초기화 작업 수행!")
    }

    @Test
    @DisplayName("메타 펀드상품정보를 저장한다.")
    @Transactional
    fun createMetaFundProduct() {
        // given
        val fundCode = "260005"
        metaFundProductRepository.save(metaFundProductEntity)

        // when
        val findMetaFundProduct = metaFundProductRepository.findByFundCode(fundCode)
        println("findMetaFundProduct = $findMetaFundProduct")

        // then
        findMetaFundProduct?.let {
            it.fundProduct.fundCode shouldBe fundCode
        }
    }
}
