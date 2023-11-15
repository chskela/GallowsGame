package com.chskela.gallowsgame.presentation.ui.components.keyboard

sealed class KeyState {
    object Default : KeyState()

    object Wrong : KeyState()

    object Correct : KeyState()
}