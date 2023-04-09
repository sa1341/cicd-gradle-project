package com.junyoung.cicdgradleproject.config.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.junyoung.cicdgradleproject.config.logger.ApiJsonLogger
import com.junyoung.cicdgradleproject.config.requestId
import com.junyoung.cicdgradleproject.config.requestUrlWithQueryString
import mu.KotlinLogging
import org.slf4j.MDC
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.time.LocalDateTime
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private val log = KotlinLogging.logger {}

@Configuration
class IoLoggingConfiguration {

    @Bean
    fun ioLoggingFilter(objectMapper: ObjectMapper, apiJsonLogger: ApiJsonLogger): OncePerRequestFilter {
        return IoLoggingFilter(objectMapper, apiJsonLogger)
    }
}

class IoLoggingFilter(
    private val objectMapper: ObjectMapper,
    private val apiJsonLogger: ApiJsonLogger
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val cachingRequest = ContentCachingRequestWrapper(request)
        val cachingResponse = ContentCachingResponseWrapper(response)

        putValueOnMDC(cachingRequest)
        doFilter(filterChain, cachingRequest, cachingResponse)
    }

    private fun doFilter(
        filterChain: FilterChain,
        cachingRequest: ContentCachingRequestWrapper,
        cachingResponse: ContentCachingResponseWrapper
    ) {
        filterChain.doFilter(cachingRequest, cachingResponse)

        apiJsonLogger.info(
            startTime = LocalDateTime.now(),
            request = cachingRequest,
            response = cachingResponse,
            requestBody = byteArrayToJson(cachingRequest.contentAsByteArray),
            responseBody = byteArrayToJson(cachingResponse.contentAsByteArray)
        )

        cachingResponse.copyBodyToResponse()
    }

    private fun byteArrayToJson(byteArray: ByteArray) =
        byteArray.let {
            if (it.isEmpty()) {
                return ""
            }

            objectMapper.readTree(it).toString()
        }

    private fun putValueOnMDC(request: HttpServletRequest) {
        request.requestId?.let {
            MDC.put(MDC_KEY_OF_REQUEST_ID, it)
        } ?: MDC.put(MDC_KEY_OF_REQUEST_ID, UUID.randomUUID().toString())
        MDC.put(MDC_KEY_OF_REQUEST_URL, request.requestUrlWithQueryString)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.requestURI.let {
            it.equals("/api/ping") || it.startsWith("/actuator")
        }
    }

    companion object {
        private const val MDC_KEY_OF_REQUEST_ID = "requestId"
        private const val MDC_KEY_OF_REQUEST_URL = "requestUrl"
    }
}
