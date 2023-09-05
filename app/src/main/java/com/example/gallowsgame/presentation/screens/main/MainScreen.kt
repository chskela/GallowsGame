package com.example.gallowsgame.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gallowsgame.presentation.screens.main.models.MainScreenUiState
import com.example.gallowsgame.presentation.ui.components.keyboard.Key
import com.example.gallowsgame.presentation.ui.components.keyboard.KeyboardGrid
import com.example.gallowsgame.presentation.ui.theme.GallowsGameTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainScreenUiState,
    onEvent: (MainScreenEvent) -> Unit = {},
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "* * * * * *",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            KeyboardGrid {
                ('А'..'Я').map { Key(letter = it) }
            }
        }

    }
}

@Preview(showBackground = true, name = "Light CurrencyScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCurrencyScreen() {
    GallowsGameTheme {
        MainScreen(
            uiState = MainScreenUiState()
        )
    }
}