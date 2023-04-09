package com.junyoung.cicdgradleproject.config.logger

import com.fasterxml.jackson.databind.ObjectMapper
import com.junyoung.cicdgradleproject.common.util.ProfileUtil
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val logger = KotlinLogging.logger("app-json-logger")

@Component
class ApiJsonLogger(
    private val objectMapper: ObjectMapper,
    private val profileUtil: ProfileUtil,
    @Value("\${spring.application.name}")
    private val appName: String
) {

    internal class LogFormat(
        val requestTime: String,
        val hostname: String,
        val port: Int,
        val method: String,
        val url: String,
        val phase: String,
        val appName: String,
        val elapsedTime: Long,
        val requestBody: String,
        val responseBody: String,
        val logType: JsonLogType = JsonLogType.INGRESS
    )

    internal class Request<T>(
        val headers: Map<String, String>,
        val body: T?
    )

    internal class Response<T>(
        val status: Int,
        val headers: Map<String, String>,
        val body: T?
    )

    fun info(
        startTime: LocalDateTime,
        request: ContentCachingRequestWrapper,
        requestBody: String,
        response: ContentCachingResponseWrapper,
        responseBody: String
    ) {
        val logFormat = LogFormat(
            hostname = request.remoteHost,
            port = request.remotePort,
            method = request.method,
            phase = profileUtil.getActiveProfile().name,
            url = request.requestURL.toString(),
            appName = appName,
            elapsedTime = ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()),
            requestTime = startTime.toString(),
            requestBody = requestBody,
            responseBody = responseBody,
            logType = JsonLogType.INGRESS
        )
        logger.info { objectMapper.writeValueAsString(logFormat) }
    }
}

enum class JsonLogType { INGRESS, EGRESS }
