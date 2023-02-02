package com.junyoung.cicdgradleproject.controller.http

import org.apache.hc.client5.http.classic.methods.HttpGet
import org.apache.hc.client5.http.fluent.Request
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.core5.http.io.entity.EntityUtils
import java.io.IOException

class HttpClient5Get {
    companion object {
        fun get(url: String): String? {
            var resultContent: String? = null
            val httpGet = HttpGet(url)
            var httpClient = HttpClients.createDefault()

            try {
                httpClient.use {
                    httpClient.use {
                        val response = it.execute(httpGet)
                        println("${response.version}")
                        println("${response.code}")
                        println(response.reasonPhrase)
                        val entity = response.entity
                        resultContent = EntityUtils.toString(entity)
                    }
                }
            } catch (ie: IOException) {
                ie.printStackTrace()
            }

            return resultContent
        }

        fun getByFluent(url: String): String? {
            var result: String? = null

            try {
                val response = Request.get(url).execute()
                result = response.returnContent().toString()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return result
        }
    }
}
