package io.github.keddnyo.quicktag.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("app_default_values")
        val FIRST_START = booleanPreferencesKey("app_first_start")
    }

    val firstStartFlow: Flow<Boolean?> = context.dataStore.data.map { preferences ->
        preferences[FIRST_START] ?: true
    }

    suspend fun appFirstStartPassed() {
        context.dataStore.edit { preferences ->
            preferences[FIRST_START] = false
        }
    }

}