package com.junyoung.cicdgradleproject.domain.product.repository

import com.junyoung.cicdgradleproject.domain.product.FundProduct
import org.springframework.data.repository.CrudRepository

interface RedisFundProductRepository : CrudRepository<FundProduct, String>
