package com.junyoung.cicdgradleproject.service

import com.junyoung.cicdgradleproject.domain.entity.FundProduct
import com.junyoung.cicdgradleproject.domain.repository.RedisFundProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class RedisFundService(
    private val redisFundProductRepository: RedisFundProductRepository
) {

    /**
     * RedisRepository는 트랜잭션을 지원하지 않음.
     */
    fun saveAllFundProduct(fundProducts: MutableList<FundProduct>) {
        fundProducts.forEach {
            cachingFundProduct(fundProduct = it)
        }
    }

    fun cachingFundProduct(fundProduct: FundProduct) {
        redisFundProductRepository.save(fundProduct)
    }


    fun getFundProduct(code: String): FundProduct? {
        return redisFundProductRepository.findByIdOrNull(code)?.let {
            it
        } ?: kotlin.run {
            throw RuntimeException("해당 펀드코드가 존재하지 않습니다.")
        }
    }

    fun getAllFundProduct(): List<FundProduct> {
        return redisFundProductRepository.findAll().toList()
    }
}
