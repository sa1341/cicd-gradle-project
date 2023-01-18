package com.junyoung.cicdgradleproject.repository

import com.junyoung.cicdgradleproject.domain.entity.FundProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FundProductRepository: JpaRepository<FundProductEntity, String> {
}
