package com.junyoung.cicdgradleproject.domain.repository

import com.junyoung.cicdgradleproject.domain.entity.MetaFundProductEntity
import com.junyoung.cicdgradleproject.domain.entity.QFundProductEntity
import com.junyoung.cicdgradleproject.domain.entity.QMetaFundProductEntity
import com.junyoung.cicdgradleproject.util.DateTimeUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class MetaFundProductRepositoryImpl :
    QuerydslRepositorySupport(MetaFundProductEntity::class.java),
    MetaFundProductRepositoryCustom {

    val qFundProductEntity: QFundProductEntity = QFundProductEntity.fundProductEntity
    val qMetaFundProductEntity: QMetaFundProductEntity = QMetaFundProductEntity.metaFundProductEntity

    override fun findByFundCode(fundCode: String): MetaFundProductEntity? {
        return from(qMetaFundProductEntity)
            .innerJoin(qMetaFundProductEntity.fundProduct, qFundProductEntity)
            .fetchJoin()
            .where(
                qFundProductEntity.fundCode.eq(fundCode)
            )
            .fetchOne()
    }

    override fun findAllExposedProducts(): List<MetaFundProductEntity> {
        return from(qMetaFundProductEntity)
            .innerJoin(qMetaFundProductEntity.fundProduct, qFundProductEntity)
            .fetchJoin()
            .where(
                qMetaFundProductEntity.expose.isTrue,
                qMetaFundProductEntity.exposeAt.before(DateTimeUtils.now())
            )
            .orderBy(qMetaFundProductEntity.exposeOrder.asc())
            .orderBy(qMetaFundProductEntity.metaFundProductId.asc())
            .fetch()
    }

    override fun findAllProductsByExposedOrderAscAndMetaFundProductIdAsc(): List<MetaFundProductEntity> {
        return from(qMetaFundProductEntity)
            .innerJoin(qMetaFundProductEntity.fundProduct, qFundProductEntity)
            .fetchJoin()
            .orderBy(qMetaFundProductEntity.exposeOrder.asc())
            .orderBy(qMetaFundProductEntity.metaFundProductId.asc())
            .fetch()
    }

    override fun findAllProducts(pageable: Pageable): Page<MetaFundProductEntity> {
        val query = from(qMetaFundProductEntity)
            .innerJoin(qMetaFundProductEntity.fundProduct, qFundProductEntity)
            .fetchJoin()

        val result = querydsl!!.applyPagination(pageable, query).fetchResults()

        return PageImpl(result.results, pageable, result.total)
    }

    override fun findAllProducts(pageable: Pageable, expose: Boolean): Page<MetaFundProductEntity> {
        val query = from(qMetaFundProductEntity)
            .innerJoin(qMetaFundProductEntity.fundProduct, qFundProductEntity)
            .fetchJoin()
            .where(
                qMetaFundProductEntity.expose.eq(expose)
            )

        val result = querydsl!!.applyPagination(pageable, query).fetchResults()

        return PageImpl(result.results, pageable, result.total)
    }

    override fun findAllProductsInFundCodes(fundCodes: List<String>): List<MetaFundProductEntity> {
        return from(qMetaFundProductEntity)
            .innerJoin(qMetaFundProductEntity.fundProduct, qFundProductEntity)
            .fetchJoin()
            .where(
                qFundProductEntity.fundCode.`in`(fundCodes)
            )
            .fetch()
    }

    override fun findAllByRiskClassificationCodeIn(
        riskClassificationCodes: List<String>,
        expose: Boolean,
        pageable: Pageable
    ): Page<MetaFundProductEntity> {

        val query = from(qMetaFundProductEntity)
            .innerJoin(
                qMetaFundProductEntity.fundProduct,
                qFundProductEntity
            )
            .fetchJoin()
            .where(
                qMetaFundProductEntity.expose.eq(expose),
                qFundProductEntity.riskClassificationCode.`in`(riskClassificationCodes)
            )
            .orderBy(qMetaFundProductEntity.exposeOrder.asc())
            .orderBy(qMetaFundProductEntity.metaFundProductId.asc())

        val result = querydsl!!.applyPagination(pageable, query).fetchResults()
        return PageImpl(result.results, pageable, result.total)
    }
}
