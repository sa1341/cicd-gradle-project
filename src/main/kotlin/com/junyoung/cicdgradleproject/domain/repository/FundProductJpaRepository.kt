package com.junyoung.cicdgradleproject.domain.repository

import com.junyoung.cicdgradleproject.domain.entity.FundProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FundProductJpaRepository: JpaRepository<FundProductEntity, String>
