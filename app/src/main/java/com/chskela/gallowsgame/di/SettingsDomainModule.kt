package com.chskela.gallowsgame.di

import com.chskela.gallowsgame.domain.settings.GetMaxNumberOfErrorsUseCase
import com.chskela.gallowsgame.domain.settings.GetNumberOfHintsUseCase
import com.chskela.gallowsgame.domain.settings.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SettingsDomainModule {

    @Provides
    fun provideGetMaxNumberOfErrorsUseCase(settingsRepository: SettingsRepository): GetMaxNumberOfErrorsUseCase {
        return GetMaxNumberOfErrorsUseCase(settingsRepository)
    }

    @Provides
    fun provideGetNumberOfHintsUseCase(settingsRepository: SettingsRepository): GetNumberOfHintsUseCase {
        return GetNumberOfHintsUseCase(settingsRepository)
    }
}