package com.chskela.gallowsgame.data

import com.chskela.gallowsgame.domain.words.WordsRepository

class WordsRepositoryImpl(private val wordStore: WordStore) : WordsRepository {
    override suspend fun getRandomWord(): String {
        TODO("Not yet implemented")
    }
}