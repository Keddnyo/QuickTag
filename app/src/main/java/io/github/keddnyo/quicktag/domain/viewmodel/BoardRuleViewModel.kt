package io.github.keddnyo.quicktag.domain.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.github.keddnyo.quicktag.data.database.room.AppRoomDatabase
import io.github.keddnyo.quicktag.data.database.room.repository.BoardRuleRoomRepository
import io.github.keddnyo.quicktag.data.datastore.AppDataStore
import io.github.keddnyo.quicktag.data.local.repository.BoardRuleLocalRepository
import io.github.keddnyo.quicktag.domain.model.BoardRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BoardRuleViewModel(application: Application) : AndroidViewModel(application) {

    private val database: BoardRuleRoomRepository
    private val dataStore: AppDataStore
    private val localRepo: BoardRuleLocalRepository

    init {
        val dao = AppRoomDatabase.getInstance(application).getRoomDao()

        database = BoardRuleRoomRepository(dao)
        dataStore = AppDataStore(application)
        localRepo = BoardRuleLocalRepository(application)

        val initialBoardRules = localRepo.getInitialBoardRules()

        createBoardRules(initialBoardRules)
    }

    fun getBoardRules() = database.getBoardRules

    fun createBoardRule(rule: BoardRule, onSuccess: () -> Unit) {
        ioThread {
            database.createBoardRule(rule) {
                mainThread {
                    onSuccess()
                }
            }
        }
    }

    fun updateBoardRule(rule: BoardRule, onSuccess: () -> Unit) {
        ioThread {
            database.updateBoardRule(rule) {
                mainThread {
                    onSuccess()
                }
            }
        }
    }

    fun deleteBoardRule(rule: BoardRule, onSuccess: () -> Unit) {
        ioThread {
            database.deleteBoardRule(rule) {
                mainThread {
                    onSuccess()
                }
            }
        }
    }

    private fun createBoardRules(initialBoardRules: List<String>) {
        ioThread {
            if (dataStore.firstStartFlow.first() == true) {
                initialBoardRules.forEach { content ->
                    val rule = BoardRule(
                        content = content.trim()
                    )
                    createBoardRule(rule) {

                    }
                }

                dataStore.appFirstStartPassed()
            }
        }
    }

    private fun mainThread(func: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            func()
        }
    }

    private fun ioThread(func: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            func()
        }
    }

    var currentBoardRule by mutableStateOf(BoardRule())
    var isBoardRuleExists by mutableStateOf(false)

}