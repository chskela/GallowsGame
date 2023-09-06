package com.chskela.gallowsgame.presentation.screens.main.models

data class MainScreenUiState(
    val word: String = "мама",
    val mask: String = word.replace(Regex("[А-яЁё]"), "*"),
    val attempts: Int = 0,
    val alphabet: List<Char> = listOf(),
    val usedLetters: Set<Char> = setOf()
)
