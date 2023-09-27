package com.chskela.gallowsgame.domain.words

import com.chskela.gallowsgame.utils.Result

interface WordsRepository {
    suspend fun getRandomWord(): Result<String>
}
