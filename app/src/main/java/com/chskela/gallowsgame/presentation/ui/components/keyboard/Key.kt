package com.chskela.gallowsgame.presentation.ui.components.keyboard

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme

@Composable
fun Key(
    modifier: Modifier = Modifier,
    letter: Char,
    enabled: Boolean,
    size: DpSize = DpSize(48.dp, 32.dp),
    onClick: () -> Unit = {}
) {

    AssistChip(
        modifier = modifier.size(
            width = size.width,
            height = size.height,
        ),
        enabled = enabled,
        onClick = onClick,
        label = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset((-4).dp),
                text = letter.toString(),
                textAlign = TextAlign.Center,
                fontSize = (size.height.value * 0.6).sp
            )
        },
    )
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButton() {
    GallowsGameTheme {
        Key(letter = 'А', enabled = true)
    }
}
