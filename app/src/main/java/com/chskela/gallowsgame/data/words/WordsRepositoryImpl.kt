package com.chskela.gallowsgame.data.words

import com.chskela.gallowsgame.domain.AbstractRepository
import com.chskela.gallowsgame.domain.words.WordsRepository
import com.chskela.gallowsgame.utils.Result
import kotlinx.coroutines.withContext

class WordsRepositoryImpl(private val wordStore: WordStore) : WordsRepository,
    AbstractRepository() {
    override suspend fun getRandomWord(): Result<String> {
        return withContext(coroutineContext) {
            try {
                Result.Success(wordStore.getRandomWord())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}