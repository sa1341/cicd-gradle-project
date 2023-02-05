package com.junyoung.cicdgradleproject.error.exception

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

private val log = KotlinLogging.logger {}

@RestControllerAdvice
class FundExceptionHandler {

    @ExceptionHandler(FundException::class)
    @ResponseBody
    fun handleFundAccidentException(e: FundException, request: HttpServletRequest): ResponseEntity<ErrorResult> {
        log.error { "ERROR = $e,  REQUEST = $request" }
        return ResponseEntity(ErrorResult.create(e), e.errorResult.status)
    }
}
