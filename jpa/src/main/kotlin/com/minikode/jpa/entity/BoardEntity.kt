package com.minikode.jpa.entity

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@org.springframework.data.relational.core.mapping.Table(
    name = "board",
    schema = "minikode"
)
@javax.persistence.Table(name = "board", catalog = "minikode", indexes = [])
class BoardEntity(
//    @Id
//    @Type(type = "uuid-char")
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Column(
//        name = "boardId",
//        nullable = false,
//        columnDefinition = "VARCHAR(36) not null comment 'pk'"
//    )
//    var boardId: UUID? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var boardId: Long? = null,

    @Column(name = "title", nullable = false) var title: String?,
    @Column(name = "description", nullable = true) var description: String?,
) {



}