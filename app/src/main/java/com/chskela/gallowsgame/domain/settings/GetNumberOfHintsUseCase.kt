package com.chskela.gallowsgame.domain.settings

import kotlinx.coroutines.flow.Flow

class GetNumberOfHintsUseCase(private val settingsRepository: SettingsRepository) {

    operator fun invoke(): Flow<Int> {
        return settingsRepository.numberOfHints()
    }
}