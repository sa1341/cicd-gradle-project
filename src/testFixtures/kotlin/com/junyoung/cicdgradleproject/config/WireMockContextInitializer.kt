package com.junyoung.cicdgradleproject.config

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent

const val WIRE_MOCK_BEAN_NAME = "wireMock"

class WireMockContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {

        val wmServer = WireMockServer(
            WireMockConfiguration()
                .port(8090)
                .withRootDirectory("src/test/resources/wiremock")
        )

        wmServer.start()

        applicationContext.beanFactory.registerSingleton(WIRE_MOCK_BEAN_NAME, wmServer)

        applicationContext.addApplicationListener {
            if (it is ContextClosedEvent) {
                wmServer.stop()
            }
        }

        // 테스트용 프로퍼티 소스를 정의할 때 사용
        TestPropertyValues
            .of("wiremock.server.port=${wmServer.port()}")
            .applyTo(applicationContext)
    }
}
