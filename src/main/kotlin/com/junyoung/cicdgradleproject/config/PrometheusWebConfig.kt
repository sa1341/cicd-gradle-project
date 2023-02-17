package com.junyoung.cicdgradleproject.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class PrometheusWebConfig : WebSecurityConfigurerAdapter() {
    @Bean
    override fun userDetailsService(): UserDetailsService {
        val user: UserDetails = User.withUsername("secactbasecret")
            .password("{noop}secretabtcacec")
            .roles("prometheus-user")
            .build()
        return InMemoryUserDetailsManager(user)
    }

    @Throws(Exception::class)
    public override fun configure(http: HttpSecurity) {
        http
            .antMatcher("/actuator/prometheus")
            .authorizeRequests()
            .anyRequest()
            .hasRole("prometheus-user").and().httpBasic()
    }
}
