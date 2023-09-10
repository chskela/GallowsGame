package com.chskela.gallowsgame.presentation.screens.main.models

import com.chskela.gallowsgame.utils.wordToMask

data class MainScreenUiState(
    val word: String = "мама",
    val mask: String = word.wordToMask(),
    val attempts: Int = 0,
    val alphabet: List<Char> = listOf(),
    val usedLetters: Set<Char> = setOf()
)
