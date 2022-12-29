package com.junyoung.cicdgradleproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class CicdGradleProjectApplication

fun main(args: Array<String>) {
    runApplication<CicdGradleProjectApplication>(*args)
}
