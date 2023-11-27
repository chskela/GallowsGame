package com.chskela.gallowsgame.presentation.screens.main.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chskela.gallowsgame.R
import com.chskela.gallowsgame.presentation.screens.main.models.MainScreenUiState
import com.chskela.gallowsgame.presentation.ui.components.gameoverdialog.GameDialog
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme

@Composable
fun MainScreenDialog(
    uiState: MainScreenUiState,
    onConfirm: () -> Unit = {}
) {
    val title = if (uiState.isGameOver) {
        stringResource(R.string.game_over)
    } else if (uiState.isWin) {
        stringResource(R.string.win)
    } else ""
    val openDialog = uiState.isGameOver || uiState.isWin

    GameDialog(word = uiState.word, title = title, openDialog = openDialog, onConfirm = onConfirm)
}

@Preview(showBackground = true, name = "Light SettingsScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDeleteDialog() {
    GallowsGameTheme {
        MainScreenDialog(uiState = MainScreenUiState(word = "Яблоко", isWin = true))
    }
}