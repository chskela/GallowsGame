package com.chskela.gallowsgame.data.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SettingsStore(private val context: Context) {

    companion object {
        private const val MAX_NUMBER_OF_ERRORS = "MAX_NUMBER_OF_ERRORS"
        private const val NUMBER_OF_HINTS = "NUMBER_OF_HINTS"
        private val MAX_NUMBER_OF_ERRORS_KEY = intPreferencesKey(MAX_NUMBER_OF_ERRORS)
        private val NUMBER_OF_HINTS_KEY = intPreferencesKey(NUMBER_OF_HINTS)
        private val Context.dataStore by preferencesDataStore("settings")
    }

    val maxNumberOfErrors: Flow<Int> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[MAX_NUMBER_OF_ERRORS_KEY] ?: Settings.MAX_NUMBER_OF_ERRORS
        }

    suspend fun setMaxNumberOfErrors(value: Int) {
        context.dataStore.edit { preferences ->
            preferences[MAX_NUMBER_OF_ERRORS_KEY] = value
        }
    }

    val numberOfHints: Flow<Int> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[NUMBER_OF_HINTS_KEY] ?: Settings.NUMBER_OF_HINTS
        }

    suspend fun setNumberOfHints(value: Int) {
        context.dataStore.edit { preferences ->
            preferences[NUMBER_OF_HINTS_KEY] = value
        }
    }
}