package com.chskela.gallowsgame.presentation.screens.main.models

import com.chskela.gallowsgame.utils.wordToMask

data class MainScreenUiState(
    val word: String = "",
    val mask: String = word.wordToMask(),
    val attempts: Int = 0,
    val alphabet: List<Char> = listOf(),
    val usedLetters: Set<Char> = setOf(),
    val isGameOver: Boolean = false,
    val isWin: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null
)
