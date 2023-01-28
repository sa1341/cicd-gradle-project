package com.junyoung.cicdgradleproject.domain.entity.ad

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import javax.persistence.AttributeConverter
import javax.persistence.Converter

private val mapper = jacksonObjectMapper()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
    .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)

@Converter
class EventContentAttributeConverter : AttributeConverter<AdEvent, String> {
    override fun convertToDatabaseColumn(attribute: AdEvent): String {
        return mapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String): AdEvent {
        return mapper.readValue(dbData, AdEvent::class.java)
    }
}

@Converter
class GuideContentAttributeConverter : AttributeConverter<AdGuide, String> {
    override fun convertToDatabaseColumn(attribute: AdGuide): String {
        return mapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String): AdGuide {
        return mapper.readValue(dbData, AdGuide::class.java)
    }
}
