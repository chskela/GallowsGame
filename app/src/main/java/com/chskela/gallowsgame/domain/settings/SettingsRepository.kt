package com.chskela.gallowsgame.domain.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun maxNumberOfErrors(): Flow<Int>

    suspend fun setMaxNumberOfErrors(value: Int)
}