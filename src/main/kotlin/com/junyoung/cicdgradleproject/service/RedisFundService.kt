package com.junyoung.cicdgradleproject.service

import com.junyoung.cicdgradleproject.config.RedisConfig
import com.junyoung.cicdgradleproject.domain.product.FundProduct
import com.junyoung.cicdgradleproject.domain.product.repository.RedisFundProductRepository
import com.junyoung.cicdgradleproject.dto.DateType
import com.junyoung.cicdgradleproject.dto.FundBuyingReq
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit

const val REDIS_FUND_KEY = "CACHE::FUND"

@Transactional
@Service
class RedisFundService(
    private val redisFundProductRepository: RedisFundProductRepository,
    private val objectRedisTemplate: RedisTemplate<String, Any>
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

    fun saveRedisObject(value: FundBuyingReq) {
        objectRedisTemplate.opsForValue().set(REDIS_FUND_KEY, value, 3, TimeUnit.MINUTES)
    }

    @Cacheable(
        value = [RedisConfig.FUND_ACCOUNT_INFO],
        cacheManager = "redisCacheManager",
        key = "{#accountNumber, #fundCod}"
    )
    fun getRedisObject(accountNumber: String, fundCod: String): FundBuyingReq? {
        return objectRedisTemplate
            .opsForValue()
            .get(REDIS_FUND_KEY) as FundBuyingReq?
            ?: throw RuntimeException("해당 키가 존재하지 않습니다.")
    }
}
