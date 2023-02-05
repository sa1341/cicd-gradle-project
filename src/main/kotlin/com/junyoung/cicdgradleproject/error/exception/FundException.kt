package com.junyoung.cicdgradleproject.error.exception

open class FundException(
    val errorResult: FundErrorResponse,
    val description: String? = null,
    override val cause: Throwable? = null
) : RuntimeException(
    """
        [ErrorCode] : ${errorResult.errorCode}
        [ErrorMessage] : ${errorResult.errorMessage}
        [Description] : $description
    """.trimIndent(),
    cause
)

class FundAccidentException(
    errorResult: FundErrorResponse,
    description: String? = null
) : FundException(errorResult, description)
