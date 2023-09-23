package com.chskela.gallowsgame.di

import com.chskela.gallowsgame.domain.words.GetRandomWordUseCase
import com.chskela.gallowsgame.domain.words.WordsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class WordsDomainModule {

    @Provides
    fun provideGetRandomWordUseCase(wordsRepository: WordsRepository): GetRandomWordUseCase {
        return GetRandomWordUseCase(wordsRepository = wordsRepository)
    }
}