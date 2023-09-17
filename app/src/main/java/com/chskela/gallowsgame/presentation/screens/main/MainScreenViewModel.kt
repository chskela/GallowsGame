package com.chskela.gallowsgame.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.gallowsgame.domain.game.CheckIsGameOverUserCase
import com.chskela.gallowsgame.domain.words.GetRandomWordUseCase
import com.chskela.gallowsgame.presentation.screens.main.models.MainScreenUiState
import com.chskela.gallowsgame.utils.wordToMask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getRandomWordUseCase: GetRandomWordUseCase,
    private val checkIsGameOverUserCase: CheckIsGameOverUserCase
) : ViewModel() {

    private val alphabet: List<Char> = ('А'..'Я').toList()

    private val _uiState = MutableStateFlow(
        MainScreenUiState(alphabet = alphabet)
    )
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(MainScreenEvent.NewGame)
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.InputChar -> {
                val newUsedLetters = _uiState.value.usedLetters + event.char

                if (_uiState.value.word.contains(event.char)) {
                    val newMask = updateMask(_uiState.value.mask, _uiState.value.word, event.char)

                    _uiState.update {
                        _uiState.value.copy(
                            mask = newMask,
                            usedLetters = newUsedLetters
                        )
                    }

                } else {
                    val newAttempts = _uiState.value.attempts + 1

                    checkIsGameOverUserCase(newAttempts)
                        .onEach { isGameOver ->
                            _uiState.update {
                                _uiState.value.copy(
                                    isGameOver = isGameOver,
                                    attempts = newAttempts,
                                    usedLetters = newUsedLetters
                                )
                            }
                        }.launchIn(viewModelScope)
                }
            }

            MainScreenEvent.NewGame -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val word = getRandomWordUseCase()
                    launch(Dispatchers.Main) {
                        _uiState.update {
                            it.copy(word = word, mask = word.wordToMask())
                        }
                    }
                }
            }
        }
    }

    private fun updateMask(mask: String, word: String, letter: Char) = mask.mapIndexed { index, c ->
        if (letter == word[index]) letter else c
    }.joinToString("")
}