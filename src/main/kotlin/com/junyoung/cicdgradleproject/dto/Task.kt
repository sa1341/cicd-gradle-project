package com.junyoung.cicdgradleproject.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Task(
    val templateName: String,
    val assignees: MutableList<String>
)
