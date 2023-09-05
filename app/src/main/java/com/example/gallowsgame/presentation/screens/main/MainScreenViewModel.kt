package com.example.gallowsgame.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.example.gallowsgame.presentation.screens.main.models.MainScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel()  {

    private val _uiState = MutableStateFlow(
        MainScreenUiState()
    )
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: MainScreenEvent){
        when(event){
            is MainScreenEvent.InputChar -> TODO()
            MainScreenEvent.NewGame -> TODO()
        }
    }
}