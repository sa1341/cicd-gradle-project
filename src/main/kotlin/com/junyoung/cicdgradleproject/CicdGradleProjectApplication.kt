package com.junyoung.cicdgradleproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CicdGradleProjectApplication

fun main(args: Array<String>) {
	runApplication<CicdGradleProjectApplication>(*args)
}
