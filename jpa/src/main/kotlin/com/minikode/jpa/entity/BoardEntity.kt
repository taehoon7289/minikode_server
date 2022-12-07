package com.minikode.jpa.entity

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "board", catalog = "minikode", indexes = [Index(
        name = "idx_board_created_date", columnList = "createdDate"
    )]
)
class BoardEntity(
    title: String, description: String?,
) : BaseEntity() {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(
        columnDefinition = "VARCHAR(36) not null comment 'pk'"
    )
    var boardId: UUID? = null

    @Column(name = "title", nullable = false)
    var title = title

    @Column(name = "description", nullable = true)
    var description = description

}