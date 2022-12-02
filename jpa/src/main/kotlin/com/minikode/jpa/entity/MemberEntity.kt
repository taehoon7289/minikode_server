package com.minikode.jpa.entity

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@org.springframework.data.relational.core.mapping.Table(
    name = "member",
    schema = "minikode"
)
@javax.persistence.Table(name = "member", catalog = "minikode", indexes = [])
class MemberEntity(
    @Column(name = "name", nullable = false)
    var name: String? = null,
) {
    @Id
//    @Type(type = "uuid-char")
    @Column(
        name = "memberId",
        nullable = false,
        columnDefinition = "VARCHAR(36) not null comment 'pk'"
    )
    var memberId: String = UUID.randomUUID().toString()


}