package com.junyoung.cicdgradleproject.util

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.time.format.SignStyle
import java.time.temporal.ChronoField.DAY_OF_MONTH
import java.time.temporal.ChronoField.HOUR_OF_DAY
import java.time.temporal.ChronoField.MINUTE_OF_HOUR
import java.time.temporal.ChronoField.MONTH_OF_YEAR
import java.time.temporal.ChronoField.SECOND_OF_MINUTE
import java.time.temporal.ChronoField.YEAR

object DateTimeUtils {

    val BIRTHDAY_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd")

    val yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val yyyy_MM_dd_dot = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    val yyyy_MM_dd_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val yyyy_MM_dd_HH_mm_ss_SSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

    val yyMMdd = DateTimeFormatter.ofPattern("yyMMdd")

    val yyyyMMdd = DateTimeFormatterBuilder()
        .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
        .appendValue(MONTH_OF_YEAR, 2)
        .appendValue(DAY_OF_MONTH, 2)
        .toFormatter()

    val yyyyMMddHHmmss = DateTimeFormatterBuilder()
        .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
        .appendValue(MONTH_OF_YEAR, 2)
        .appendValue(DAY_OF_MONTH, 2)
        .appendValue(HOUR_OF_DAY, 2)
        .appendValue(MINUTE_OF_HOUR, 2)
        .appendValue(SECOND_OF_MINUTE, 2)
        .toFormatter()

    val yyyyMM = DateTimeFormatter.ofPattern("yyyyMM")

    val GMT_PLUS_9 = ZoneId.of("GMT+9")

    val HHmmss = DateTimeFormatter.ofPattern("HHmmss")

    /**
     * Mysql의 Datetime은 초단위까지만 저장하므로
     * 테스트 등에서 equal method가 쓸데없이 실패하는 경우를 방지하기 위해 사용.
     *
     * @return 현재시각을 초단위까지 계산한 값.
     */
    fun now(): LocalDateTime {
        return LocalDateTime.now().withNano(0)
    }

    fun fromEpochMillisWithGmtPlus9(millis: Long): LocalDateTime {
        return Instant.ofEpochMilli(millis).atZone(GMT_PLUS_9).toLocalDateTime()
    }

    fun fromEpochSecondisWithGmtPlus9(second: Long): LocalDateTime {
        return Instant.ofEpochSecond(second).atZone(GMT_PLUS_9).toLocalDateTime()
    }

    fun toGmt9EpochMillis(localDateTime: LocalDateTime): Long {
        return ZonedDateTime.of(localDateTime, GMT_PLUS_9).toInstant().toEpochMilli()
    }

    fun nowInGmt9EpochMillis(): Long {
        return toGmt9EpochMillis(now())
    }

    /**
     * parses yyyy-MM-dd or yyyyMMdd
     *
     * @param string to be parsed
     * @return parsed LocalDate value
     * @throws NullPointerException   if string is null
     * @throws DateTimeParseException if string is not parsable
     */
    fun parseLocalDate(string: String?): LocalDate {
        if (string.isNullOrBlank()) {
            throw NullPointerException("Fail to parse LocalDate of null. ")
        }

        return try {
            LocalDate.parse(string, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: DateTimeParseException) {
            LocalDate.parse(string, yyyyMMdd)
        }
    }

    fun parseLocalDateOrNull(string: String?): LocalDate? {
        if (string.isNullOrBlank()) {
            return null
        }

        return try {
            LocalDate.parse(string, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: DateTimeParseException) {
            return try {
                LocalDate.parse(string, yyyyMMdd)
            } catch (e: Exception) {
                null
            }
        }
    }

    fun parseLocalDateOrNull(string: String?, formatter: DateTimeFormatter): LocalDate? {
        if (string == null) {
            return null
        }

        return try {
            LocalDate.parse(string, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    fun parseLocalDateTime(string: String?): LocalDateTime {
        if (string.isNullOrBlank()) {
            throw NullPointerException("Fail to parse LocalDate of null. ")
        }

        return try {
            LocalDateTime.parse(string, yyyy_MM_dd_HH_mm_ss)
        } catch (e: DateTimeParseException) {
            LocalDateTime.parse(string, yyyy_MM_dd_HH_mm_ss_SSS)
        }
    }

    fun parseLocalTimeOrNull(string: String?): LocalTime? {
        if (string.isNullOrBlank()) {
            return null
        }

        return try {
            LocalTime.parse(string, HHmmss)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    fun format(dateTime: LocalDateTime, format: String): String {
        val dateTimeFormatter = DateTimeFormatter.ofPattern(format)
        return dateTime.format(dateTimeFormatter)
    }

    fun format(date: LocalDate, format: String): String {
        val dateTimeFormatter = DateTimeFormatter.ofPattern(format)
        return date.format(dateTimeFormatter)
    }

    fun getYearMonthByString(yearMonth: String): YearMonth {
        return YearMonth.parse(yearMonth, yyyyMM)
    }

    fun getStartMonthByYearMonth(yearMonth: YearMonth): LocalDateTime {
        return yearMonth.atDay(1).atStartOfDay()
    }

    fun getEndMonthByYearMonth(yearMonth: YearMonth): LocalDateTime {
        return yearMonth.atEndOfMonth().atStartOfDay().plusDays(1).minusSeconds(1)
    }

    fun toStringFromNowDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        return current.format(formatter)
    }

    fun toStringFromNowTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HHmmssSSSSSS")
        return current.format(formatter)
    }
}
