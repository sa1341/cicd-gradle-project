package com.junyoung.cicdgradleproject.domain.product.meta.repository

import com.junyoung.cicdgradleproject.domain.product.meta.MetaFundProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MetaFundProductRepository : JpaRepository<MetaFundProductEntity, Long>, MetaFundProductRepositoryCustom {
    fun findByMetaFundProductId(metaFundProductId: Long): MetaFundProductEntity?
    fun countByExpose(expose: Boolean): Long
}
