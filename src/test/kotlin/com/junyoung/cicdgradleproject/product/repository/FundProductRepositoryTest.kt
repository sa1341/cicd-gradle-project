package com.junyoung.cicdgradleproject.product.repository

import com.junyoung.cicdgradleproject.domain.product.QFundProductEntity
import com.junyoung.cicdgradleproject.product.data.fundProductEntity
import com.junyoung.cicdgradleproject.repository.FundProductRepository
import com.querydsl.jpa.impl.JPAQueryFactory
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

    @Autowired
    lateinit var jpaQueryFactory: JPAQueryFactory

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
        val findFundProduct = jpaQueryFactory.selectFrom(QFundProductEntity.fundProductEntity)
            .where(QFundProductEntity.fundProductEntity.fundCode.eq(fundCode))
            .fetchOne()

        // then
        findFundProduct?.productName shouldBe "키움똑똑이"
    }
}
