package com.chskela.gallowsgame.presentation.ui.components.keyboard

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Key(
    modifier: Modifier = Modifier,
    letter: Char,
    enabled: Boolean,
    onClick: () -> Unit = {}
) {

    AssistChip(
        modifier = modifier.sizeIn(50.dp, 32.dp, 90.dp, 32.dp),
        enabled = enabled,
        onClick = onClick,
        label = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset((-4).dp),
                text = letter.toString(),
                textAlign = TextAlign.Center
            )
        },
//        leadingIcon = {
//            Icon(
//                Icons.Filled.Settings,
//                contentDescription = "Localized description",
//                Modifier.size(AssistChipDefaults.IconSize)
//            )
//        }

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
