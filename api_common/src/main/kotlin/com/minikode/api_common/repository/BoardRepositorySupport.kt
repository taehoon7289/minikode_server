package com.minikode.api_common.repository

import com.minikode.api_common.projection.BoardInfo
import com.minikode.jpa.entity.BoardEntity
import com.minikode.jpa.entity.QBoardEntity
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class BoardRepositorySupport(
    private val jpaQueryFactory: JPAQueryFactory,
) {

    fun get(): MutableList<BoardEntity> {
        return jpaQueryFactory.select(QBoardEntity.boardEntity)
            .from(QBoardEntity.boardEntity).fetch()
    }

}