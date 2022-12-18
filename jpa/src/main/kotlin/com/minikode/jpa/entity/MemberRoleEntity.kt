package com.minikode.jpa.entity

import com.minikode.jpa.entity.embeddable.MemberRoleId
import javax.persistence.*

@Entity
@Table(
    name = "member_role",
    catalog = "minikode",
    indexes = [Index(
        name = "idx_member_role_created_date",
        columnList = "createdDate"
    )]
)
@Embeddable
class MemberRoleEntity(
    name: String,
    role: String,
) : BaseEntity() {

    @EmbeddedId
    var memberRoleId: MemberRoleId? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_roles")
    var member: MemberEntity? = null

}