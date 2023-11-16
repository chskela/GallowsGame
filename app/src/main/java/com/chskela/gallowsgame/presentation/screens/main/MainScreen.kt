package com.chskela.gallowsgame.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import com.chskela.gallowsgame.presentation.ui.components.error.Error
import com.chskela.gallowsgame.presentation.ui.components.gameoverdialog.GameDialog
import com.chskela.gallowsgame.presentation.ui.components.icons.HintIcon
import com.chskela.gallowsgame.presentation.ui.components.icons.RefreshIcon
import com.chskela.gallowsgame.presentation.ui.components.keyboard.Key
import com.chskela.gallowsgame.presentation.ui.components.keyboard.KeyboardGrid
import com.chskela.gallowsgame.presentation.ui.components.mask.Mask
import com.chskela.gallowsgame.presentation.ui.components.spacers.VerticalSpacer8Dp
import com.chskela.gallowsgame.presentation.ui.components.topappbar.GallowsTopAppBar
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme
import com.chskela.gallowsgame.utils.WindowInfo
import com.chskela.gallowsgame.utils.calculateSizeItem

@Composable
fun MainScreen(
    uiState: MainScreenUiState,
    onEvent: (MainScreenEvent) -> Unit = {},
    windowInfo: WindowInfo
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

    Scaffold(topBar = {
        GallowsTopAppBar(
            title = stringResource(id = R.string.app_name),
            navigationIcon = {
                Spacer(modifier = Modifier.size(48.dp))
            },
            actions = {
                IconButton(
                    onClick = { onEvent(MainScreenEvent.Hint) },
                    enabled = uiState.isHintEnable,
                    content = { HintIcon() }
                )
                IconButton(
                    onClick = { onEvent(MainScreenEvent.NewGame) },
                    content = { RefreshIcon() }
                )
            })
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (uiState.error != null) {
                Error(error = uiState.error)
            } else {
                Row(modifier = Modifier.weight(0.5f)) {
                    if (uiState.isLoading) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                        )
                    } else {
                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            VerticalSpacer8Dp()
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(R.string.attempts, uiState.attempts),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Text(
                                    text = stringResource(R.string.hints, uiState.hints),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }
                            VerticalSpacer8Dp()
                            Mask(mask = uiState.mask)
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.Bottom
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
                VerticalSpacer8Dp()
            }
        }
    }
}

@Preview(
    showBackground = true, name = "Light CurrencyScreen", showSystemUi = true,
    device = "spec:parent=pixel_xl,orientation=landscape"
)
@Preview(
    showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "id:Nexus 10"
)
@Composable
private fun PreviewMainScreen() {
    GallowsGameTheme {
        MainScreen(
            uiState = MainScreenUiState(
                alphabet = ('А'..'Я').toList(),
                word = "ввваввапвапвпрв",
                isLoading = false
            ),
            windowInfo = WindowInfo(),
        )
    }
}

@Preview(showBackground = true, name = "Light CurrencyScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewMainScreenShort() {
    GallowsGameTheme {
        MainScreen(
            uiState = MainScreenUiState(
                alphabet = ('А'..'Я').toList(), word = "вв",
                isLoading = false,
            ),
            windowInfo = WindowInfo(),
        )
    }
}