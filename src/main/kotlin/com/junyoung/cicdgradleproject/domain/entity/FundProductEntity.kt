package com.junyoung.cicdgradleproject.domain.entity

import com.junyoung.cicdgradleproject.const.FundType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "fund_product")
@Entity
class FundProductEntity(

    @Id
    val code: String,
    val name: String,
    val type: FundType
)
