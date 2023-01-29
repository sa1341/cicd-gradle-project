package com.junyoung.cicdgradleproject.domain.product.meta.repository

import com.junyoung.cicdgradleproject.domain.product.meta.MetaFundProductOptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MetaFundProductOptionRepository : JpaRepository<MetaFundProductOptionEntity, Long>
