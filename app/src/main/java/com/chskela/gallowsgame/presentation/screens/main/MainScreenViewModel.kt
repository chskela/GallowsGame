package com.chskela.gallowsgame.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.gallowsgame.domain.game.CheckIsGameOverUserCase
import com.chskela.gallowsgame.domain.words.GetRandomWordUseCase
import com.chskela.gallowsgame.presentation.screens.main.models.MainScreenUiState
import com.chskela.gallowsgame.utils.Result
import com.chskela.gallowsgame.utils.wordToMask
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val initState = MainScreenUiState(alphabet = alphabet)

    private val _uiState = MutableStateFlow(initState)
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(MainScreenEvent.NewGame)
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.InputChar -> {
                val newUsedLetters = _uiState.value.usedLetters + event.char

                if (_uiState.value.word.contains(event.char)) {
                    val newMask = updateMask(
                        mask = _uiState.value.mask,
                        word = _uiState.value.word,
                        letter = event.char
                    )

                    _uiState.update {
                        _uiState.value.copy(
                            isWin = _uiState.value.word == newMask,
                            mask = newMask,
                            usedLetters = newUsedLetters,
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
                _uiState.update {
                    initState.copy(
                        isLoading = true
                    )
                }

                viewModelScope.launch {
                    val newState = when (val result = getRandomWordUseCase()) {
                        is Result.Success -> {
                            val word = result.data.uppercase()
                            initState.copy(
                                word = word,
                                mask = word.wordToMask(),
                                isLoading = false
                            )
                        }

                        is Result.Error -> {
                            initState.copy(
                                error = "Что-то пошло не так",
                                isLoading = false
                            )
                        }
                    }
                    _uiState.update { newState }
                }
            }
        }
    }

    private fun updateMask(mask: String, word: String, letter: Char) = mask.mapIndexed { index, c ->
        if (letter == word[index]) letter else c
    }.joinToString("")
}