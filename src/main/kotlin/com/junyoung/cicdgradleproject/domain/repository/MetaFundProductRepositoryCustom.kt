package com.junyoung.cicdgradleproject.domain.repository

import com.junyoung.cicdgradleproject.domain.entity.MetaFundProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MetaFundProductRepositoryCustom {

    fun findByFundCode(fundCode: String): MetaFundProductEntity?

    fun findAllExposedProducts(): List<MetaFundProductEntity>

    fun findAllProductsByExposedOrderAscAndMetaFundProductIdAsc(): List<MetaFundProductEntity>

    fun findAllProducts(pageable: Pageable): Page<MetaFundProductEntity>

    fun findAllProducts(pageable: Pageable, expose: Boolean): Page<MetaFundProductEntity>

    fun findAllProductsInFundCodes(fundCodes: List<String>): List<MetaFundProductEntity>

    fun findAllByRiskClassificationCodeIn(
        riskClassificationCodes: List<String>,
        expose: Boolean,
        pageable: Pageable
    ): Page<MetaFundProductEntity>
}
