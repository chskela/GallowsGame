package com.chskela.gallowsgame.domain.words

import kotlinx.coroutines.flow.Flow

interface WordsRepository {
    fun getRandomWord(): Flow<String>
}
