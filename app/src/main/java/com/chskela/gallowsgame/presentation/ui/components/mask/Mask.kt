package com.chskela.gallowsgame.presentation.ui.components.mask

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme

@Composable
fun Mask(modifier: Modifier = Modifier, mask: String) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        val sizeItem = (maxWidth - 6.dp * (mask.length - 1)) / mask.length
        Row(horizontalArrangement = Arrangement.Center) {
            mask.mapIndexed { index, letter ->
                Box(
                    modifier = Modifier
                        .sizeIn(maxWidth = sizeItem.coerceAtMost(64.dp))
                        .aspectRatio(1f)
                        .background(color = MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = letter.toString(), color = MaterialTheme.colorScheme.onPrimary)
                }
                if (index != mask.lastIndex) {
                    Spacer(modifier = Modifier.size(4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMonoButton() {
    GallowsGameTheme {
        Mask(mask = "A***V*")
    }
}
