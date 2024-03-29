package com.junyoung.cicdgradleproject.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.junyoung.cicdgradleproject.error.handler.FundErrorResponseHandler
import mu.KotlinLogging
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClients
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.time.Duration

private val log = KotlinLogging.logger {}
const val API_ROOT_URL = "http://localhost:8082"

@Configuration
class RestTemplateConfig {

    @Bean
    fun apiRestTemplate(
        objectMapper: ObjectMapper,
        errorHandlerObjectMapper: ObjectMapper
    ): RestTemplate {
        val httpClient: HttpClient = HttpClients.custom()
            .setMaxConnTotal(200)
            .setMaxConnPerRoute(100)
            .build()
        val factory = HttpComponentsClientHttpRequestFactory(httpClient)
        val converter = MappingJackson2HttpMessageConverter(objectMapper)
        return RestTemplateBuilder()
            .requestFactory { BufferingClientHttpRequestFactory(factory) }
            .additionalMessageConverters(converter)
            .additionalInterceptors(ApiLoggingInterceptor(objectMapper))
            .errorHandler(FundErrorResponseHandler(errorHandlerObjectMapper))
            .setConnectTimeout(Duration.ofSeconds(1))
            .setReadTimeout(Duration.ofSeconds(15))
            .build()
    }
}

private class ApiLoggingInterceptor(
    private val objectMapper: ObjectMapper
) : ClientHttpRequestInterceptor {

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        val requestBody = byteArrayToJson(body)
        log.info(
            """REQUEST
            | Request URI : ${request.uri} 
            | Request Method : ${request.method} 
            | Request Header : ${request.headers} 
            | Request Body : $requestBody 
            """.trimMargin()
        )

        try {
            return execution.execute(request, body).also {
                log.info(
                    """RESPONSE
                | Request URI : ${request.uri} 
                | Request Method : ${request.method} 
                | Request Header : ${request.headers} 
                | Request Body : $requestBody 
                | Response Status : ${it.statusCode} 
                | Response Body : ${byteArrayToJson(it.body.readBytes())}
                    """.trimMargin()
                )
            }
        } catch (e: Exception) {
            log.error { "ERROR: $e" }
            throw e
        }
    }

    private fun byteArrayToJson(byteArray: ByteArray) =
        byteArray.let {
            if (it.isEmpty()) {
                return ""
            }
            objectMapper.readTree(it).toString()
        }
}
