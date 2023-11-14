package com.chskela.gallowsgame.domain.words

import kotlinx.coroutines.flow.Flow

class GetRandomWordUseCase(private val wordsRepository: WordsRepository) {
    operator fun invoke(): Flow<String> {
        return wordsRepository.getRandomWord()
    }
}