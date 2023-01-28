package com.junyoung.cicdgradleproject.domain.repository

import com.junyoung.cicdgradleproject.domain.entity.MetaFundProductOptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MetaFundProductOptionRepository : JpaRepository<MetaFundProductOptionEntity, Long>
