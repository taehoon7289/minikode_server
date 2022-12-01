package com.minikode.api_common.projection

import java.util.*

/**
 * A Projection for the {@link com.minikode.jpa.entity.BoardEntity} entity
 */
interface BoardInfo {
    val title: String?
    val description: String?
    val boardId: String?
}