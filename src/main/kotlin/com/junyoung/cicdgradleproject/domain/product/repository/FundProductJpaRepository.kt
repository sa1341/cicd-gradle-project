package com.junyoung.cicdgradleproject.domain.product.repository

import com.junyoung.cicdgradleproject.domain.product.FundProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FundProductJpaRepository : JpaRepository<FundProductEntity, String>
