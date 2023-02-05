package com.junyoung.cicdgradleproject.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class JacksonConfig {
    @Primary
    @Bean
    fun restTemplateObjectMapper(): ObjectMapper {
        return jacksonObjectMapper()
            .registerModule(
                JavaTimeModule().addSerializer(
                    LocalDate::class.java,
                    LocalDateSerializer(DateTimeFormatter.ISO_DATE)
                ).addSerializer(
                    LocalDateTime::class.java,
                    LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN))
                )
            )
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
    }

    @Bean
    fun errorHandlerObjectMapper(): ObjectMapper {
        return jacksonObjectMapper()
            .registerModule(Jdk8Module())
            .registerModule(
                JavaTimeModule().addSerializer(
                    LocalDate::class.java,
                    LocalDateSerializer(DateTimeFormatter.ISO_DATE)
                )
            )
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
    }

    companion object {
        const val DEFAULT_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    }
}
