package com.junyoung.cicdgradleproject.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class DateTypeBuilder(
val localDate: LocalDate = LocalDate.now(),
val localDateTime: LocalDateTime? = LocalDateTime.now()
) {
fun build(): DateType {
return DateType(
localDate = localDate,
localDateTime = localDateTime
)
}
}
