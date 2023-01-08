package com.minikode.jpa.entity

import com.minikode.common.code.AdminRole
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "admin",
    catalog = "minikode",
    indexes = [Index(
        name = "idx_admin_created_date",
        columnList = "createdDate"
    )]
)
class AdminEntity(
    accessId: String = "",
    password: String = "",
    name: String = "",
    email: String = "",
    role: AdminRole,
) : BaseEntity() {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(
        columnDefinition = "VARCHAR(36) not null comment 'pk'"
    )
    var adminId: UUID? = null

    @Column(name = "accessId", nullable = false, unique = true)
    var accessId = accessId

    @Column(name = "password", nullable = true)
    var password = password

    @Column(name = "name", nullable = true)
    var name = name

    @Column(name = "email", nullable = true)
    var email = email

    @Column(name = "role", nullable = false)
    var role: AdminRole = role

}