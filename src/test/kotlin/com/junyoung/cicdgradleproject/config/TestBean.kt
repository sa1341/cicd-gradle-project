package com.junyoung.cicdgradleproject.config

import com.junyoung.cicdgradleproject.dto.DateType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@Configuration
class TestBean {

    @Bean
    fun dateType(): DateType {
        return DateType(LocalDate.now(), null)
    }
}
