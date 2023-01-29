package com.junyoung.cicdgradleproject.domain.product.meta.ad

import com.junyoung.cicdgradleproject.domain.product.meta.MetaFundProductEntity
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("EVENT")
data class MetaFundProductAdEventEntity(

    override val metaFundProduct: MetaFundProductEntity,
    override val expose: Boolean = false,
    override val exposeOrder: Int = Int.MAX_VALUE,

    @Convert(converter = EventContentAttributeConverter::class)
    @Column(name = "ad_content")
    val adEvent: AdEvent

) : AbstractMetaFundProductAdEntity(
    metaFundProduct = metaFundProduct,
    expose = expose,
    exposeOrder = exposeOrder
)
