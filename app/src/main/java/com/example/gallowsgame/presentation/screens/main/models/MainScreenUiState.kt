package com.example.gallowsgame.presentation.screens.main.models

data class MainScreenUiState(
    val word: String = "мама",
    val mask: String = word.replace(Regex("[А-яЁё]"), "*"),
    val attempts: Int = 0,
    val usedLetters: Set<Char> = emptySet()
)
