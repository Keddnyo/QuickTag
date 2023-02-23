package io.github.keddnyo.quicktag.data.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.keddnyo.quicktag.domain.model.BoardRule

@Dao
interface BoardRuleRoomDao {

    @Query("SELECT * FROM rules")
    fun getBoardRules(): LiveData<List<BoardRule>>

    @Insert
    suspend fun createBoardRule(rule: BoardRule)

    @Update
    suspend fun updateBoardRule(rule: BoardRule)

    @Delete
    suspend fun deleteBoardRule(rule: BoardRule)

}