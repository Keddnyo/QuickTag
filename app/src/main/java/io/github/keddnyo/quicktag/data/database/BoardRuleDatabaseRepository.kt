package io.github.keddnyo.quicktag.data.database

import androidx.lifecycle.LiveData
import io.github.keddnyo.quicktag.domain.model.BoardRule

interface BoardRuleDatabaseRepository {

    val getBoardRules: LiveData<List<BoardRule>>

    suspend fun createBoardRule(rule: BoardRule, onSuccess: () -> Unit)

    suspend fun updateBoardRule(rule: BoardRule, onSuccess: () -> Unit)

    suspend fun deleteBoardRule(rule: BoardRule, onSuccess: () -> Unit)

}