package com.chskela.gallowsgame.domain.words

class GetRandomWordUseCase(private val wordsRepository: WordsRepository) {
    suspend operator fun invoke(): String {
        return wordsRepository.getRandomWord().uppercase()
    }
}