package com.junyoung.cicdgradleproject.domain.product.meta.ad.repository

import com.junyoung.cicdgradleproject.domain.product.meta.ad.MetaFundProductAdEventEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MetaFundProductAdEventRepository :
    JpaRepository<MetaFundProductAdEventEntity, Long>,
    MetaFundProductAdEventRepositoryCustom
