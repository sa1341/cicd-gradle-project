package com.junyoung.cicdgradleproject.domain.repository

import com.junyoung.cicdgradleproject.domain.entity.FundProduct
import org.springframework.data.repository.CrudRepository

interface RedisFundProductRepository : CrudRepository<FundProduct, String>
