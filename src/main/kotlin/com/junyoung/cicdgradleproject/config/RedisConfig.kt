package com.junyoung.cicdgradleproject.config

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val logger = KotlinLogging.logger {}

@Configuration
@EnableCaching
class RedisConfig(
    @Value("\${spring.redis.host}") private val host: String,
    @Value("\${spring.redis.port}") private val port: String,
    @Value("\${spring.redis.password:}") private val password: String,
    private val phase: Phase
) {
    @Bean
    fun stringRedisTemplate(): StringRedisTemplate {
        return StringRedisTemplate(redisConnectionFactory())
    }

    private val defaultObjectMapper = jacksonObjectMapper()
        .activateDefaultTyping(
            BasicPolymorphicTypeValidator
                .builder()
                .allowIfBaseType(Any::class.java)
                .build(),
            ObjectMapper.DefaultTyping.EVERYTHING,
            JsonTypeInfo.As.PROPERTY
        )
        .registerModule(
            JavaTimeModule()
                .addSerializer(
                    LocalDate::class.java,
                    LocalDateSerializer(DateTimeFormatter.ISO_DATE)
                ).addSerializer(
                    LocalDateTime::class.java,
                    LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN))
                )
        )
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port.toInt())
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val redisTemplate = RedisTemplate<Any, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }

    @Bean
    fun objectRedisTemplate(context: ApplicationContext): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer(defaultObjectMapper)
        return redisTemplate
    }

    @Bean
    @Primary
    fun redisCacheManager(context: ApplicationContext): CacheManager {
        val jsonSerializer = getJsonSerializer()
        val configuration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(jsonSerializer)
            .computePrefixWith { cacheName ->
                "${CACHE_PREFIX}${phase.name}::$cacheName::"
            }
            .entryTtl(Duration.ofSeconds(60))
        return RedisCacheManager.builder(redisConnectionFactory())
            .cacheDefaults(configuration)
            .transactionAware()
            .build()
    }

    private fun getJsonSerializer(): RedisSerializationContext.SerializationPair<Any> {
        return RedisSerializationContext
            .SerializationPair
            .fromSerializer(GenericJackson2JsonRedisSerializer(defaultObjectMapper))
    }

    companion object {
        const val DEFAULT_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS"
        val DAY_SECENDS: Long = 60 * 60 * 24
        val CACHE_PREFIX = "CACHE::FUND::"
        const val FUND_ACCOUNT_INFO = "FUND_ACCOUNT_INFO"
    }
}
