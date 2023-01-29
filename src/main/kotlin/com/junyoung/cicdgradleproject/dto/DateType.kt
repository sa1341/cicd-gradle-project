package com.junyoung.cicdgradleproject.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class DateType(
    val localDate: LocalDate,
    val localDateTime: LocalDateTime?
)
