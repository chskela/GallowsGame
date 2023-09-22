package com.chskela.gallowsgame.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.gallowsgame.R
import com.chskela.gallowsgame.presentation.screens.main.models.MainScreenUiState
import com.chskela.gallowsgame.presentation.ui.components.gameoverdialog.GameDialog
import com.chskela.gallowsgame.presentation.ui.components.keyboard.Key
import com.chskela.gallowsgame.presentation.ui.components.keyboard.KeyboardGrid
import com.chskela.gallowsgame.presentation.ui.components.mask.Mask
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainScreenUiState,
    onEvent: (MainScreenEvent) -> Unit = {},
) {
    val title = if (uiState.isGameOver) {
        stringResource(R.string.game_over)
    } else if (uiState.isWin) {
        stringResource(R.string.win)
    } else ""

    GameDialog(
        word = uiState.word,
        title = title,
        openDialog = uiState.isGameOver || uiState.isWin,
        onConfirm = {
            onEvent(MainScreenEvent.NewGame)
        }
    )

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ошибки: ${uiState.attempts}",
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(modifier = Modifier.height(16.dp))
                Mask(mask = uiState.mask)
            }

            if (uiState.alphabet.isNotEmpty()) {
                KeyboardGrid {
                    uiState.alphabet.map { letter ->
                        val isUsed = uiState.usedLetters.contains(letter)
                        Key(
                            letter = letter,
                            enabled = !isUsed,
                            onClick = { onEvent(MainScreenEvent.InputChar(letter)) })
                    }
                }
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
            uiState = MainScreenUiState(alphabet = ('А'..'Я').toList(), word = "ЯБЛОКО")
        )
    }
}