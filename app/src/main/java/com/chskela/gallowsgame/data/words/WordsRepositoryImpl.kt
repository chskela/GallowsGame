package com.chskela.gallowsgame.data.words

import com.chskela.gallowsgame.domain.words.WordsRepository

class WordsRepositoryImpl(private val wordStore: WordStore) : WordsRepository {
    override suspend fun getRandomWord(): String {
        return wordStore.getRandomWord()
    }
}