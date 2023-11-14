package com.chskela.gallowsgame.data.words

import com.chskela.gallowsgame.domain.AbstractRepository
import com.chskela.gallowsgame.domain.words.WordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

class WordsRepositoryImpl(private val wordStore: WordStore) : WordsRepository,
    AbstractRepository() {

    override fun getRandomWord(): Flow<String> {
        return wordStore.getRandomWord()
            .distinctUntilChanged()
            .flowOn(coroutineContext)
    }
}