package com.junyoung.cicdgradleproject.domain.product.meta.ad.repository

import com.junyoung.cicdgradleproject.domain.product.meta.ad.MetaFundProductAdGuideEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MetaFundProductAdGuideRepository :
    JpaRepository<MetaFundProductAdGuideEntity, Long>,
    MetaFundProductAdGuideRepositoryCustom
