package com.junyoung.cicdgradleproject.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/ping")
    fun ping() = "pong"
}
