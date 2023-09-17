package com.chskela.gallowsgame.di

import android.content.Context
import com.chskela.gallowsgame.data.settings.SettingsRepositoryImpl
import com.chskela.gallowsgame.data.settings.SettingsStore
import com.chskela.gallowsgame.data.words.WordStore
import com.chskela.gallowsgame.data.words.WordsRepositoryImpl
import com.chskela.gallowsgame.domain.settings.SettingsRepository
import com.chskela.gallowsgame.domain.words.WordsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun getWordStore(@ApplicationContext appContext: Context): WordStore {
        return WordStore(context = appContext)
    }

    @Provides
    @Singleton
    fun getWordsRepository(wordStore: WordStore): WordsRepository {
        return WordsRepositoryImpl(wordStore = wordStore)
    }

    @Provides
    @Singleton
    fun getSettingsStore(@ApplicationContext appContext: Context): SettingsStore {
        return SettingsStore(appContext)
    }

    @Provides
    @Singleton
    fun getSettingsRepository(settingsStore: SettingsStore): SettingsRepository {
        return SettingsRepositoryImpl(settingsStore = settingsStore)
    }
}