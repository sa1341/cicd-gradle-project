package com.junyoung.cicdgradleproject.domain.entity

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class TagsAttributeConverter : AttributeConverter<List<String>, String?> {
    override fun convertToDatabaseColumn(attribute: List<String>): String? {
        return attribute.filter { it.isNotBlank() }.joinToString(",")
    }

    override fun convertToEntityAttribute(dbData: String?): List<String> {
        return dbData?.split(',')?.filter { it.isNotBlank() } ?: emptyList()
    }
}
