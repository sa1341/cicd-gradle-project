package com.junyoung.cicdgradleproject.config

import javax.servlet.http.HttpServletRequest

private const val REQUEST_ID_HEADER_KEY = "x-request-id"

internal val HttpServletRequest.requestId: String?
    get() = this.getHeader(REQUEST_ID_HEADER_KEY)

internal val HttpServletRequest.requestUrlWithQueryString: String
    get() = "${this.requestURL}${this.queryString?.let { "?$it" } ?: ""}"

internal val HttpServletRequest.joinedHeaderString: String
    get() = this.headerNames.toList().joinToString { "$it:${this.getHeader(it)}" }
