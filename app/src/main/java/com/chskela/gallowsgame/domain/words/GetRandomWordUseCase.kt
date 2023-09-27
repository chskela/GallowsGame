package com.chskela.gallowsgame.domain.words

import com.chskela.gallowsgame.utils.Result

class GetRandomWordUseCase(private val wordsRepository: WordsRepository) {
    suspend operator fun invoke(): Result<String> {
        return wordsRepository.getRandomWord()
    }
}