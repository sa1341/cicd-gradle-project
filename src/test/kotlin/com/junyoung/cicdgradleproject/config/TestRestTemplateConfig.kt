package com.junyoung.cicdgradleproject.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClients
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.time.Duration

@TestConfiguration
class TestRestTemplateConfig {
    @Bean
    fun apiRestTemplate(objectMapper: ObjectMapper): RestTemplate {
        val httpClient: HttpClient = HttpClients.custom()
            .setMaxConnTotal(100)
            .setMaxConnPerRoute(20)
            .build()
        val factory = HttpComponentsClientHttpRequestFactory(httpClient)
        val converter = MappingJackson2HttpMessageConverter(objectMapper)
        return RestTemplateBuilder()
            .rootUri("http://localhost/test-config-bean")
            .requestFactory { BufferingClientHttpRequestFactory(factory) }
            .additionalMessageConverters(converter)
            .setConnectTimeout(Duration.ofSeconds(1))
            .setReadTimeout(Duration.ofSeconds(10))
            .build()
    }
}
