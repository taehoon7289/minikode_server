package com.minikode.jpa.entity

import com.minikode.jpa.entity.embeddable.MemberRoleEmbed
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
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
    var memberRoleId: MemberRoleEmbed? = null

}