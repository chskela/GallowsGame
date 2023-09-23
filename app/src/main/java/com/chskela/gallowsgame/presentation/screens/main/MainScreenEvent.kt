package com.chskela.gallowsgame.presentation.screens.main

sealed class MainScreenEvent {
    data class InputChar(val char: Char) : MainScreenEvent()

    object NewGame : MainScreenEvent()
}