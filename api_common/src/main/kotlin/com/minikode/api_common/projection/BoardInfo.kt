package com.minikode.api_common.projection

import java.time.LocalDateTime
import java.util.*

/**
 * A Projection for the {@link com.minikode.jpa.entity.BoardEntity} entity
 */
interface BoardInfo {
    val createdDate: LocalDateTime?
    val lastModifiedDate: LocalDateTime?
    val boardId: UUID?
    val title: String?
    val description: String?
}