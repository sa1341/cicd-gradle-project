package com.junyoung.cicdgradleproject.domain.entity.ad

import com.junyoung.cicdgradleproject.domain.entity.MetaFundProductEntity
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("GUIDE")
data class MetaFundProductAdGuideEntity(
    override val metaFundProduct: MetaFundProductEntity,
    override val expose: Boolean = false,
    override val exposeOrder: Int = Int.MAX_VALUE,

    @Convert(converter = GuideContentAttributeConverter::class)
    @Column(name = "ad_content")
    val adGuide: AdGuide

) : AbstractMetaFundProductAdEntity(
    metaFundProduct = metaFundProduct,
    expose = expose,
    exposeOrder = exposeOrder
)
