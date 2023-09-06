package com.chskela.gallowsgame.domain.words

interface WordsRepository {
    suspend fun getRandomWord(): String
}
