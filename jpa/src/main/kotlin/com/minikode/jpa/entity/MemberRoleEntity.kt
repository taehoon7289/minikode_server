package com.minikode.jpa.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.minikode.common.code.MemberRole
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "member_role",
    catalog = "minikode",
    indexes = []
)
@Embeddable
class MemberRoleEntity(
    role: MemberRole,
    member: MemberEntity,
) {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(
        columnDefinition = "VARCHAR(36) not null comment 'pk'"
    )
    var memberRoleId: UUID? = null

    @Enumerated(EnumType.STRING)
    var role: MemberRole = role

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "member_id",
        columnDefinition = "VARCHAR(36) not null comment 'fk'"
    )
    var member: MemberEntity = member

}