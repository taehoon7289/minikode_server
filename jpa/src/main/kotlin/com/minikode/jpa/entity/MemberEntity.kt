package com.minikode.jpa.entity

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "member",
    catalog = "minikode",
    indexes = [Index(
        name = "idx_member_created_date",
        columnList = "createdDate"
    )]
)
class MemberEntity(
    accessId: String,
    name: String,
    email: String?,
) : BaseEntity() {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(
        columnDefinition = "VARCHAR(36) not null comment 'pk'"
    )
    var memberId: UUID? = null

    @Column(name = "accessId", nullable = false)
    var accessId = accessId

    @Column(name = "name", nullable = true)
    var name = name

    @Column(name = "email", nullable = true)
    var email = email

}