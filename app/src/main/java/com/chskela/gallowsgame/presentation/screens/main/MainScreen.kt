package com.chskela.gallowsgame.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.chskela.gallowsgame.presentation.screens.main.components.MainScreenDialog
import com.chskela.gallowsgame.presentation.screens.main.components.MainScreenKeyboard
import com.chskela.gallowsgame.presentation.screens.main.components.MainScreenTopAppBar
import com.chskela.gallowsgame.presentation.screens.main.models.MainScreenUiState
import com.chskela.gallowsgame.presentation.ui.components.error.Error
import com.chskela.gallowsgame.presentation.ui.components.gallowsview.ImageOfGallows
import com.chskela.gallowsgame.presentation.ui.components.mask.Mask
import com.chskela.gallowsgame.presentation.ui.components.spacers.VerticalSpacer8Dp
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme
import com.chskela.gallowsgame.utils.WindowInfo

@Composable
fun MainScreen(
    uiState: MainScreenUiState,
    onEvent: (MainScreenEvent) -> Unit = {},
    windowInfo: WindowInfo
) {
    MainScreenDialog(
        uiState = uiState,
        onConfirm = { onEvent(MainScreenEvent.NewGame) }
    )

    Scaffold(topBar = {
        MainScreenTopAppBar(isHintEnable = uiState.isHintEnable, onEvent = onEvent)
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
                Row(modifier = Modifier.weight(0.6f)) {
                    if (uiState.isLoading) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                        )
                    } else {
                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.15f),
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
                            Mask(modifier = Modifier.weight(0.25f), mask = uiState.mask)
                            ImageOfGallows(
                                modifier = Modifier.weight(0.6f),
                                stage = uiState.attempts
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MainScreenKeyboard(
                        windowInfo = windowInfo,
                        uiState = uiState,
                        onEvent = onEvent
                    )
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