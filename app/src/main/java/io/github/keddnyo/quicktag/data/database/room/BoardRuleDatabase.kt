package io.github.keddnyo.quicktag.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.keddnyo.quicktag.data.database.room.dao.BoardRuleRoomDao
import io.github.keddnyo.quicktag.domain.model.BoardRule

@Database(entities = [BoardRule::class], version = 1, exportSchema = false)
abstract class BoardRuleDatabase : RoomDatabase() {
    abstract fun getRoomDao(): BoardRuleRoomDao

    companion object {
        private var INSTANCE: BoardRuleDatabase? = null

        fun getInstance(context: Context): BoardRuleDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context, BoardRuleDatabase::class.java, "board_rules"
                ).build()

                INSTANCE as BoardRuleDatabase
            } else {
                INSTANCE as BoardRuleDatabase
            }
        }
    }
}