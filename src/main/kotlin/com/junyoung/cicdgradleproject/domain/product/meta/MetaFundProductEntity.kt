package com.junyoung.cicdgradleproject.domain.product.meta

import com.junyoung.cicdgradleproject.domain.product.FundProductEntity
import com.junyoung.cicdgradleproject.domain.product.meta.ad.MetaFundProductAdEventEntity
import com.junyoung.cicdgradleproject.domain.product.meta.ad.MetaFundProductAdGuideEntity
import org.hibernate.annotations.BatchSize
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.OrderBy
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@DynamicUpdate
@Table(name = "meta_fund_product")
data class MetaFundProductEntity(
    @Id
    @Column(name = "meta_fund_product_id")
    val metaFundProductId: Long? = null,

    // 연관관계가 ManyToOne, OneToOne 경우에 부모 엔티티의 식별키 값을 자식 엔티티에서 기본키 및 외래키 지정을 위해 사용함.
    @OneToOne
    @MapsId
    @JoinColumn(name = "meta_fund_product_id", nullable = false, updatable = false)
    val fundProduct: FundProductEntity,

    @OneToMany(mappedBy = "metaFundProduct", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @OrderBy("expose_order, meta_fund_product_ad_id")
    @BatchSize(size = 10)
    val metaFundProductAdEvents: List<MetaFundProductAdEventEntity> = emptyList(),

    @OneToMany(mappedBy = "metaFundProduct", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @OrderBy("expose_order, meta_fund_product_ad_id")
    @BatchSize(size = 10)
    val metaFundProductAdGuides: List<MetaFundProductAdGuideEntity> = emptyList(),

    @OneToMany(mappedBy = "metaFundProduct", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @BatchSize(size = 10)
    val metaFundProductOptions: List<MetaFundProductOptionEntity> = emptyList(),

    @Column(name = "title")
    val title: String? = null,

    @Column(name = "list_thumbnail_url")
    val listThumbnailUrl: String? = null,

    @Column(name = "list_thumbnail_emoji")
    val listThumbnailEmoji: String? = null,

    @Column(name = "detail_thumbnail_url")
    val detailThumbnailUrl: String? = null,

    @Column(name = "expose", columnDefinition = "BIT", nullable = false)
    val expose: Boolean = false,

    @field:NotNull
    @Column(name = "expose_order")
    val exposeOrder: Int = Integer.MAX_VALUE,

    @Column(name = "expose_at")
    val exposeAt: LocalDateTime? = null,

    @field:NotNull
    @Column(name = "genie_product")
    val genieProduct: Boolean = false,

    @Convert(converter = TagsAttributeConverter::class)
    @Column(name = "tags", nullable = true)
    val tags: List<String> = emptyList(),

    @field:NotNull
    @Column(name = "profit_visible")
    val profitVisible: Boolean = true,

    @Column(name = "zeroin_fund_updated_at")
    val zeroinFundUpdatedAt: LocalDateTime? = null,

    @Column(name = "zeroin_fund_price_updated_at")
    val zeroinFundPriceUpdatedAt: LocalDateTime? = null,

    @Column(name = "zeroin_fund_asset_ratio_updated_at")
    val zeroinFundAssetRatioUpdatedAt: LocalDateTime? = null,

    @Column(name = "investment_guide_updated_at")
    val investmentGuideUpdatedAt: LocalDateTime? = null,

    @Column(name = "zeroin_fund_profit_rate_updated_at")
    val zeroinFundProfitRateUpdatedAt: LocalDateTime? = null,

    @Column(name = "ad_review_statement")
    val adReviewStatement: String? = null,

    @Column(name = "rgba_color")
    val rgbaColor: String? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "created_by")
    val createdBy: String,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_by")
    val updatedBy: String
)
