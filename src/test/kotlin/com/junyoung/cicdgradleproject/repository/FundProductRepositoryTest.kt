package com.junyoung.cicdgradleproject.repository

import com.junyoung.cicdgradleproject.const.FundType
import com.junyoung.cicdgradleproject.domain.entity.FundProductEntity
import com.junyoung.cicdgradleproject.domain.repository.FundProductJpaRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@SpringBootTest
class FundProductRepositoryTest {

    @Autowired
    lateinit var repository: FundProductJpaRepository

    @Rollback(value = false)
    @Transactional
    @Test
    fun saveFundProduct() {
        // given
        val fundProduct = FundProductEntity("260005", "한화쏠쏠", FundType.STOCK)

        // when
        repository.save(fundProduct)

        // then
    }

    @Transactional
    @Test
    fun getFundProduct() {
        // given
        val code = "260005"

        // when
        val findProduct = repository.findByIdOrNull(code)

        // then
        Assertions.assertThat(findProduct?.code).isEqualTo("260005")
    }
}
