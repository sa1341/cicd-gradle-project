package com.junyoung.cicdgradleproject.domain.common

import org.springframework.context.annotation.Configuration
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class AuditEntity {

    @Column(nullable = false, updatable = false)
    @CreatedDate
    open var createdAt: LocalDateTime = LocalDateTime.now()

    @CreatedBy
    open var createdBy: String = ""

    @LastModifiedDate
    open var updatedAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedBy
    open var updatedBy: String = ""
}

@EnableJpaAuditing
@Configuration
class JpaAuditConfiguration
