package com.chskela.gallowsgame.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.chskela.gallowsgame.presentation.screens.main.models.MainScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {

    private val alphabet: List<Char> = ('А'..'Я').toList()

    private val _uiState = MutableStateFlow(
        MainScreenUiState(alphabet = alphabet)
    )
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.InputChar -> {
                _uiState.update {
                    it.copy(usedLetters = _uiState.value.usedLetters + event.char)
                }
            }

            MainScreenEvent.NewGame -> TODO()
        }
    }
}