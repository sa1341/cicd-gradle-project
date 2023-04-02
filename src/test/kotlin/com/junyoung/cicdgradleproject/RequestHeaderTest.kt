package com.junyoung.cicdgradleproject

import org.springframework.http.HttpHeaders

fun main() {
    val headers = HttpHeaders()
    headers.set("Accept", "application/json")
    headers.set("Accept-Encoding", "gzip")
    headers.set("Accept-Language", "en-KR")
    headers.set("Content-Type", "application/json")
    val map = headers.keys.toList().associateWith { key -> headers[key] }
    println(map.toString())
}
