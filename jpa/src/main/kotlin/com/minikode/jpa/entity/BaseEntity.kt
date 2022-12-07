package com.minikode.jpa.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @Column(
        nullable = false,
        columnDefinition = "datetime(6) not null comment '등록시간'",
        updatable = false
    )
    @CreatedDate
    lateinit var createdDate: LocalDateTime

    @Column(
        nullable = false,
        columnDefinition = "datetime(6) not null comment '최근수정시간'",
    )
    @LastModifiedDate
    lateinit var lastModifiedDate: LocalDateTime

}