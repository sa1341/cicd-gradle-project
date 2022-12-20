package com.junyoung.cicdgradleproject.controller.http

fun main() {
    //val result = HttpClient5Get.get("http://httpbin.org/get")
    val result = HttpClient5Get.getByFluent("http://httpbin.org/get")
    println("result = $result")
}
