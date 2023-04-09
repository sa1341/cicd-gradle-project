package com.junyoung.cicdgradleproject.error.exception

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.http.HttpStatus

interface ResultCode {
    val code: String
    val message: String
    val status: HttpStatus
}

data class CommonError(
    val message: String,
    val status: Int,
    val errors: MutableList<String>
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class FundErrorResponse(
    val errorCode: String,
    val errorMessage: String,
    val errorStatus: HttpStatus? = null
) : ResultCode {
    override val code = errorCode
    override val message = errorMessage
    override val status: HttpStatus = errorStatus ?: getHttpStatus()

    private fun getHttpStatus(): HttpStatus {
        return when {
            FundErrorCode.values().any { it.name == errorCode } -> FundErrorCode.valueOf(errorCode).httpStatus
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }
    }

    enum class FundErrorCode(val httpStatus: HttpStatus, val message: String) {
        BANK_ACCOUNT_BLOCKED(HttpStatus.BAD_REQUEST, "거래가 제한된 계좌"),
        INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러"),
    }
}

data class ErrorResult(
    val errorCode: String,
    val errorMessage: String,
    val status: HttpStatus
) {
    companion object {
        fun create(
            e: FundException
        ): ErrorResult {
            return ErrorResult(e.errorResult.errorCode, e.errorResult.errorMessage, e.errorResult.status)
        }

        fun create(
            e: Exception
        ): ErrorResult {
            return ErrorResult(
                HttpStatus.INTERNAL_SERVER_ERROR.value().toString(),
                "SERVER_ERROR",
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }
}
