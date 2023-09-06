package com.chskela.gallowsgame.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.chskela.gallowsgame.presentation.screens.main.MainScreen
import com.chskela.gallowsgame.presentation.screens.main.MainScreenViewModel
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainScreenViewModel:MainScreenViewModel by viewModels()
            val uiState by mainScreenViewModel.uiState.collectAsState()
            GallowsGameTheme {
                MainScreen(
                    uiState = uiState,
                    onEvent = mainScreenViewModel::onEvent
                )
            }
        }
    }
}
