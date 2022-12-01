package com.minikode.api_common.repository

import com.minikode.api_common.projection.BoardInfo
import com.minikode.jpa.entity.QBoardEntity
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class BoardRepositorySupport(
    private val jpaQueryFactory: JPAQueryFactory,
) {

    fun get(): MutableList<BoardInfo> {
        return jpaQueryFactory.select(
            Projections.fields(
                BoardInfo::class.java,
                QBoardEntity.boardEntity.boardId,
                QBoardEntity.boardEntity.title,
                QBoardEntity.boardEntity.description,
            )
        ).from(QBoardEntity.boardEntity).fetch()
    }

}