package com.junyoung.cicdgradleproject.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.junyoung.cicdgradleproject.dto.AgitPostReq
import com.junyoung.cicdgradleproject.dto.AgitPostRes
import com.junyoung.cicdgradleproject.dto.Task
import mu.KotlinLogging
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.core5.http.ContentType
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.apache.hc.core5.http.io.entity.StringEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

private val logger = KotlinLogging.logger {}

@RequestMapping("/api/agit")
@RestController
class AgitApi(
    private val httpClient: CloseableHttpClient,
    private val mapper: ObjectMapper
) {

    @PostMapping("/article")
    fun sendPost(): ResponseEntity<AgitPostRes> {

        val serviceUrl = "https://agit.com/send"
        logger.debug { "Service Url: $serviceUrl" }

        val task = Task(templateName = "펀드/연금 일일점검", assignees = mutableListOf("jean.calm"))
        val agitPostReq = AgitPostReq(text = createText(), task)

        val jsonBody = mapper.writeValueAsString(agitPostReq)
        logger.debug { "JSON BODY = $jsonBody" }

        val httpPost = HttpPost(serviceUrl)
        httpPost.entity = StringEntity(jsonBody, ContentType.APPLICATION_JSON)

        try {
            val response = httpClient.execute(httpPost)
            val result = EntityUtils.toString(response.entity)
            val agitPostRes = mapper.readValue(result, AgitPostRes::class.java)
            logger.debug { "response: $agitPostRes" }
            return ResponseEntity.ok().body(agitPostRes)
        } catch (ie: IOException) {
            logger.error { "ERROR: $ie" }
            throw ie
        }
    }

    private fun createText(): String {
        return """
            > 일자: 12/22 (목)
            주요점검사항
            - [x] 제로인 데이터 수신여부 확인
            - [x] 펀드 기준가 등록, 업무개시 확인
            - [x] 수정기준가 생성여부 확인
            - [x] 결산, 환매확정, 환매결제, 매수결제 미처리 대상건 여부 확인
            - [x] 예약 → 정규 배치 미처리건 확인(발생할 수 있음)
            - [x] 숙려제도 자동취소 미처리건 확인
            - [x] 펀드 회계 마감 완료여부 확인
            - [x] 고객잔고, 상품잔고 일치여부 확인
        """.trimIndent()
    }
}
