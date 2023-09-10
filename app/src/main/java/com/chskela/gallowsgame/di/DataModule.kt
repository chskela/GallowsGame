package com.chskela.gallowsgame.di

import android.content.Context
import com.chskela.gallowsgame.data.WordStore
import com.chskela.gallowsgame.data.WordsRepositoryImpl
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
        return WordStore("singular.txt", appContext)
    }

    @Provides
    @Singleton
    fun getWordsRepository(wordStore: WordStore): WordsRepository {
        return WordsRepositoryImpl(wordStore = wordStore)
    }
}