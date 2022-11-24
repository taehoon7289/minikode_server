package com.minikode.jpa.entity

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "board", catalog = "minikode", indexes = [])
class BoardEntity {
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

    @Column(name = "title", nullable = false)
    var title: String? = null

}