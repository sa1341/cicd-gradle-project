package com.junyoung.cicdgradleproject.service

import com.junyoung.cicdgradleproject.domain.entity.FundProductEntity
import com.junyoung.cicdgradleproject.dto.FundProductReq
import com.junyoung.cicdgradleproject.repository.FundProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FundProductService(
    private val fundProductRepository: FundProductRepository
) {

    @Transactional(readOnly = true)
    fun getFundProduct(fundCode: String): FundProductEntity? {
        return fundProductRepository.findByIdOrNull(fundCode)?.let {
            it
        } ?: kotlin.run {
            throw RuntimeException("해당 펀드코드가 존재하지 않습니다.")
        }
    }

    @Transactional
    fun saveFundProduct(fundProductReq: FundProductReq) {
        val fundProduct = FundProductEntity(code = fundProductReq.fundCode, name = fundProductReq.name, fundProductReq.type)
        fundProductRepository.save(fundProduct)
    }
}
