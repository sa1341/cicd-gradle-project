package com.junyoung.cicdgradleproject.service

import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class FundProductSyncScheduler {

    @Scheduled(cron = "0 * * * * ?")
    fun cronJobSchedule() {
        logger.debug { "Task Start ${Thread.currentThread().name}" }
    }
}
