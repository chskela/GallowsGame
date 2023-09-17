package com.chskela.gallowsgame.presentation.ui.components.gameoverdialog

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chskela.gallowsgame.R
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme


@Composable
fun GameDialog(
    word: String,
    title: String,
    openDialog: Boolean,
    onConfirm: () -> Unit = {}
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.hidden_word, word),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(
                        text = stringResource(R.string.new_game),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true, name = "Light SettingsScreen", showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDeleteDialog() {
    GallowsGameTheme {
        GameDialog(word = "ЯБЛОКО", title = stringResource(R.string.game_over), true)
    }
}