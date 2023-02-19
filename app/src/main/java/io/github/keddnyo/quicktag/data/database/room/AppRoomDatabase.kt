package io.github.keddnyo.quicktag.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.keddnyo.quicktag.data.database.room.dao.BoardRuleRoomDao
import io.github.keddnyo.quicktag.domain.model.BoardRule

@Database(entities = [BoardRule::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getRoomDao(): BoardRuleRoomDao

    companion object {
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context = context,
                    klass = AppRoomDatabase::class.java,
                    name = "quick_tag_database"
                ).build()
                INSTANCE as AppRoomDatabase
            } else {
                INSTANCE as AppRoomDatabase
            }
        }
    }
}