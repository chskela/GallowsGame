package com.chskela.gallowsgame.presentation.screens.main.components

import androidx.compose.runtime.Composable
import com.chskela.gallowsgame.presentation.screens.main.MainScreenEvent
import com.chskela.gallowsgame.presentation.screens.main.models.MainScreenUiState
import com.chskela.gallowsgame.presentation.ui.components.keyboard.Key
import com.chskela.gallowsgame.presentation.ui.components.keyboard.KeyboardGrid
import com.chskela.gallowsgame.utils.WindowInfo
import com.chskela.gallowsgame.utils.calculateSizeItem

@Composable
fun MainScreenKeyboard(
    windowInfo: WindowInfo,
    uiState: MainScreenUiState,
    onEvent: (MainScreenEvent) -> Unit = {},
) {
    if (uiState.alphabet.isNotEmpty()) {
        val size = calculateSizeItem(windowInfo, uiState.alphabet.size)
        KeyboardGrid {
            uiState.alphabet.map { letter ->
                val isUsed = uiState.usedLetters.contains(letter)
                val isCorrectLetter = uiState.word.contains(letter)

                Key(
                    letter = letter,
                    enabled = !isUsed,
                    isCorrectLetter = isCorrectLetter,
                    size = size,
                    onClick = { onEvent(MainScreenEvent.InputChar(letter)) })
            }
        }
    }

}
