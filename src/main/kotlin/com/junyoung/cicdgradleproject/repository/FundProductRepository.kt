package com.junyoung.cicdgradleproject.repository

import com.junyoung.cicdgradleproject.domain.product.FundProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FundProductRepository : JpaRepository<FundProductEntity, Long> {
    fun findByFundCode(fundCode: String): FundProductEntity?
}
