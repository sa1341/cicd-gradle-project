package com.junyoung.cicdgradleproject.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.junyoung.cicdgradleproject.domain.common.AuditEntity
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "meta_fund_product_option")
@DynamicUpdate
data class MetaFundProductOptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_fund_product_option_id")
    val metaFundProductOptionId: Long? = null,

    @ManyToOne
    @JoinColumn(name = "meta_fund_product_id", nullable = false, updatable = false)
    @JsonIgnore
    val metaFundProduct: MetaFundProductEntity,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "option_name")
    val option: FundProductOption
) : AuditEntity()
