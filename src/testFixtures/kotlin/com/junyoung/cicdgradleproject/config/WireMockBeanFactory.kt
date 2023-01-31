package com.junyoung.cicdgradleproject.config

import com.github.tomakehurst.wiremock.WireMockServer
import org.springframework.beans.factory.BeanFactory
import org.springframework.stereotype.Component

@Component
class WireMockBeanFactory(
    private val beanFactory: BeanFactory
) {

    fun fundWireMock() =
        beanFactory.getBean(
            WIRE_MOCK_BEAN_NAME,
            WireMockServer::class.java
        )
}
