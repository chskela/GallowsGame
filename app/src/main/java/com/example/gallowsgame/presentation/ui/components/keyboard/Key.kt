package com.example.gallowsgame.presentation.ui.components.keyboard

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gallowsgame.presentation.ui.theme.GallowsGameTheme


@Composable
fun Key(
    modifier: Modifier = Modifier,
    letter: Char,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .size(70.dp)
            .clickable(onClick = onClick, enabled = enabled),
        contentAlignment = Alignment.Center
    ) {
        Text(text = letter.toString(), color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButton() {
    GallowsGameTheme {
        Key(letter = 'А')
    }
}
