package com.chskela.gallowsgame.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.gallowsgame.domain.game.CheckIsGameOverUserCase
import com.chskela.gallowsgame.domain.settings.GetNumberOfHintsUseCase
import com.chskela.gallowsgame.domain.words.GetRandomWordUseCase
import com.chskela.gallowsgame.presentation.screens.main.models.MainScreenUiState
import com.chskela.gallowsgame.utils.wordToMask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getRandomWordUseCase: GetRandomWordUseCase,
    private val checkIsGameOverUserCase: CheckIsGameOverUserCase,
    private val getNumberOfHintsUseCase: GetNumberOfHintsUseCase
) : ViewModel() {

    private val alphabet: List<Char> = ('А'..'Я').toList()
    private val initState = MainScreenUiState(alphabet = alphabet)

    private val _uiState = MutableStateFlow(initState)
    val uiState = _uiState.asStateFlow()

    private val setForHint = MutableStateFlow(emptySet<Char>())

    init {
        onEvent(MainScreenEvent.NewGame)
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.InputChar -> {
                val inputLetter = event.char
                val word = _uiState.value.word
                val newUsedLetters = _uiState.value.usedLetters + inputLetter

                if (word.contains(inputLetter)) {
                    updateSetForHint(inputLetter)

                    val newMask = updateMask(
                        mask = _uiState.value.mask,
                        word = word,
                        letter = inputLetter
                    )

                    _uiState.update {
                        it.copy(
                            isWin = word == newMask,
                            mask = newMask,
                            usedLetters = newUsedLetters,
                        )
                    }

                } else {
                    val newAttempts = _uiState.value.attempts + 1

                    checkIsGameOverUserCase(newAttempts)
                        .onEach { isGameOver ->
                            _uiState.update {
                                it.copy(
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
                combine(getRandomWordUseCase(), getNumberOfHintsUseCase()) { word, hints ->

                    val newWord = word.uppercase()
                    _uiState.update {
                        initState.copy(
                            word = newWord,
                            mask = newWord.wordToMask(),
                            hints = hints,
                            isLoading = false
                        )
                    }
                    initSetForHint()
                }
                    .catch {
                        _uiState.update {
                            initState.copy(
                                error = "Что-то пошло не так",
                                isLoading = false
                            )
                        }
                    }.launchIn(viewModelScope)
            }

            MainScreenEvent.Hint -> {
                if (setForHint.value.isNotEmpty()) {
                    _uiState.update {
                        val newHints = it.hints - 1
                        it.copy(
                            hints = newHints,
                            isHintEnable = newHints > 0
                        )
                    }
                    val hintLetter = setForHint.value.random()
                    onEvent(MainScreenEvent.InputChar(hintLetter))
                }
            }
        }
    }

    private fun initSetForHint() {
        setForHint.update {
            _uiState.value.word.toSet()
        }
    }

    private fun updateSetForHint(letter: Char) {
        setForHint.update {
            it.minus(letter)
        }
    }

    private fun updateMask(mask: String, word: String, letter: Char) = mask.mapIndexed { index, c ->
        if (letter == word[index]) letter else c
    }.joinToString("")
}