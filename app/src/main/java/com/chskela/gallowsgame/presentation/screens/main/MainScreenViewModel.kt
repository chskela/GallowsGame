package com.chskela.gallowsgame.presentation.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.gallowsgame.domain.words.GetRandomWordUseCase
import com.chskela.gallowsgame.presentation.screens.main.models.MainScreenUiState
import com.chskela.gallowsgame.utils.wordToMask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getRandomWordUseCase: GetRandomWordUseCase
) : ViewModel() {

    private val alphabet: List<Char> = ('А'..'Я').toList()

    private val _uiState = MutableStateFlow(
        MainScreenUiState(alphabet = alphabet)
    )
    val uiState = _uiState.asStateFlow()

    init {
        Log.e("TEST", "init")
        onEvent(MainScreenEvent.NewGame)
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.InputChar -> {
                _uiState.update {
                    it.copy(usedLetters = _uiState.value.usedLetters + event.char)
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
}