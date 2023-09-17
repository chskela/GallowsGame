package com.chskela.gallowsgame.data.settings

import com.chskela.gallowsgame.domain.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(private val settingsStore: SettingsStore) : SettingsRepository {
    override fun maxNumberOfErrors(): Flow<Int> {
        return settingsStore.maxNumberOfErrors
    }

    override suspend fun setMaxNumberOfErrors(value: Int) {
        settingsStore.setMaxNumberOfErrors(value)
    }
}