package io.github.keddnyo.quicktag.data.database.room.repository

import androidx.lifecycle.LiveData
import io.github.keddnyo.quicktag.data.database.BoardRuleDatabaseRepository
import io.github.keddnyo.quicktag.data.database.room.dao.BoardRuleRoomDao
import io.github.keddnyo.quicktag.domain.model.BoardRule

class BoardRuleRoomRepository(private val dao: BoardRuleRoomDao) : BoardRuleDatabaseRepository {
    override val getBoardRules: LiveData<List<BoardRule>>
        get() = dao.getBoardRules()

    override suspend fun createBoardRule(rule: BoardRule, onSuccess: () -> Unit) {
        dao.createBoardRule(rule)
        onSuccess()
    }

    override suspend fun updateBoardRule(rule: BoardRule, onSuccess: () -> Unit) {
        dao.updateBoardRule(rule)
        onSuccess()
    }

    override suspend fun deleteBoardRule(rule: BoardRule, onSuccess: () -> Unit) {
        dao.deleteBoardRule(rule)
        onSuccess()
    }
}