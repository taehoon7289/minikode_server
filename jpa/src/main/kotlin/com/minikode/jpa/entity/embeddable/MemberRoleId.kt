package com.minikode.jpa.entity.embeddable

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class MemberRoleId(
    name: String,
    role: String,
) : Serializable {

    @Column(name = "name", nullable = true)
    var name = name

    @Column(name = "role", nullable = true)
    var role = role

}