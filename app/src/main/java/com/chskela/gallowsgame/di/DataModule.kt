package com.chskela.gallowsgame.di

import com.chskela.gallowsgame.data.WordStore
import com.chskela.gallowsgame.data.WordsRepositoryImpl
import com.chskela.gallowsgame.domain.words.WordsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun getWordStore(): WordStore {
        return WordStore("singular.txt")
    }

    @Provides
    @Singleton
    fun getWordsRepository(wordStore: WordStore): WordsRepository {
        return WordsRepositoryImpl(wordStore = wordStore)
    }
}