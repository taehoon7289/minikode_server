package com.minikode.jpa.entity

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "member", catalog = "minikode", indexes = [])
class MemberEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(
        name = "uuid",
        nullable = false,
        columnDefinition = "VARCHAR(36) not null comment 'uuid'"
    )
    var uuid: UUID? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

}