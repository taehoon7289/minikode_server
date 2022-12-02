package com.minikode.jpa.entity

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@org.springframework.data.relational.core.mapping.Table
@javax.persistence.Table(name = "board", catalog = "minikode", indexes = [])
class BoardEntity(
    @Column(name = "title", nullable = false) var title: String,
    @Column(name = "description", nullable = true) var description: String,
) {
    @Id
    @Type(type = "uuid-char")
    @Column(
        name = "uuid",
        nullable = false,
        columnDefinition = "VARCHAR(36) not null comment 'uuid'"
    )
    var boardId: UUID = UUID.randomUUID()


}