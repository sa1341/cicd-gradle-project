package com.junyoung.cicdgradleproject.config

import org.apache.hc.client5.http.classic.HttpClient
import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.client5.http.io.HttpClientConnectionManager
import org.apache.hc.core5.util.Timeout
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HttpClientConfig {

    @Bean
    fun httpClient(): CloseableHttpClient {
        return HttpClients.custom()
            .setDefaultRequestConfig(requestConfig())
            .setConnectionManager(connectionManager())
            .build()
    }

    fun requestConfig(): RequestConfig {
        return RequestConfig.custom()
            .setConnectTimeout(Timeout.ofMilliseconds(5000))
            .setConnectionRequestTimeout(Timeout.ofMilliseconds(5000))
            .setResponseTimeout(Timeout.ofMilliseconds(5000))
            .build()
    }

    fun connectionManager(): HttpClientConnectionManager {
        return PoolingHttpClientConnectionManagerBuilder.create()
            .setMaxConnTotal(100)
            .setMaxConnPerRoute(100)
            .build()
    }
}
