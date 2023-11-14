package com.chskela.gallowsgame.data.settings

import com.chskela.gallowsgame.domain.AbstractRepository
import com.chskela.gallowsgame.domain.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

class SettingsRepositoryImpl(private val settingsStore: SettingsStore) : SettingsRepository,
    AbstractRepository() {

    override fun maxNumberOfErrors(): Flow<Int> {
        return settingsStore.maxNumberOfErrors
            .distinctUntilChanged()
            .flowOn(coroutineContext)
    }

    override suspend fun setMaxNumberOfErrors(value: Int) {
        settingsStore.setMaxNumberOfErrors(value)
    }

    override fun numberOfHints(): Flow<Int> {
        return settingsStore.numberOfHints
            .distinctUntilChanged()
            .flowOn(coroutineContext)
    }

    override suspend fun setNumberOfHints(value: Int) {
        settingsStore.setNumberOfHints(value)
    }
}