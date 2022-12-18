package com.minikode.api_common.projection

import java.time.LocalDateTime
import java.util.*

/**
 * A Projection for the {@link com.minikode.jpa.entity.BoardEntity} entity
 */
class BoardInfo {
    var createdDate: LocalDateTime? = null
    val lastModifiedDate: LocalDateTime? = null
    val boardId: UUID? = null
    val title: String? = null
    val description: String? = null
}