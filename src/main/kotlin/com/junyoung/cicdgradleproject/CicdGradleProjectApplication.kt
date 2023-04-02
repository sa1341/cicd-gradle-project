package com.junyoung.cicdgradleproject

import com.junyoung.cicdgradleproject.const.FundType
import com.junyoung.cicdgradleproject.dto.FundProductReq
import com.junyoung.cicdgradleproject.service.FundProductService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class CicdGradleProjectApplication(
    private val fundProductService: FundProductService
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        fundProductService.saveFundProduct(
            fundProductReq = FundProductReq(
                fundCode = "206005",
                name = "키움똑똑한 펀드",
                type = FundType.STOCK
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<CicdGradleProjectApplication>(*args)
}
