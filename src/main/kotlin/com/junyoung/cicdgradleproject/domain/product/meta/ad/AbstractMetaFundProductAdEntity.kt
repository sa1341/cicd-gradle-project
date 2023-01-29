package com.junyoung.cicdgradleproject.domain.product.meta.ad

import com.fasterxml.jackson.annotation.JsonIgnore
import com.junyoung.cicdgradleproject.domain.common.AuditEntity
import com.junyoung.cicdgradleproject.domain.product.meta.MetaFundProductEntity
import org.hibernate.annotations.DiscriminatorOptions
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "meta_fund_product_ad")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ad_type")
@DiscriminatorOptions(force = true)
@DynamicUpdate
abstract class AbstractMetaFundProductAdEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_fund_product_ad_id")
    val adId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_product_id", nullable = false, updatable = false)
    @JsonIgnore
    open val metaFundProduct: MetaFundProductEntity,

    @field:NotNull
    @Column(name = "expose")
    open val expose: Boolean = false,

    @field:NotNull
    @Column(name = "expose_order")
    open val exposeOrder: Int = Integer.MAX_VALUE
) : AuditEntity()
