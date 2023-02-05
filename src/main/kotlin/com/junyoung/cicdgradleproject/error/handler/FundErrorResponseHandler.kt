package com.junyoung.cicdgradleproject.error.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.junyoung.cicdgradleproject.error.exception.FundAccidentException
import com.junyoung.cicdgradleproject.error.exception.FundErrorResponse
import mu.KotlinLogging
import org.springframework.http.client.ClientHttpResponse
import org.springframework.util.StreamUtils
import org.springframework.web.client.ResponseErrorHandler
import java.nio.charset.Charset

private val logger = KotlinLogging.logger {}

class FundErrorResponseHandler(
    private val errorHandlerObjectMapper: ObjectMapper
) : ResponseErrorHandler {
    override fun hasError(response: ClientHttpResponse): Boolean {
        return response.statusCode.isError
    }

    override fun handleError(response: ClientHttpResponse) {
        val res = StreamUtils.copyToString(response.body, Charset.defaultCharset())
        val errorRes = errorHandlerObjectMapper.readValue(res, FundErrorResponse::class.java)
        logger.error { "Error Response = $errorRes" }

        if (errorRes.errorCode == FundErrorResponse.FundErrorCode.BANK_ACCOUNT_BLOCKED.name) {
            throw FundAccidentException(errorRes)
        }
    }
}
