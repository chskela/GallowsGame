package com.chskela.gallowsgame.presentation.ui.components.mask

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chskela.gallowsgame.presentation.ui.components.spacers.HorizontalSpacer4Dp
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme
import com.chskela.gallowsgame.utils.animation.shake.rememberShakeController
import com.chskela.gallowsgame.utils.animation.shake.shake

@Composable
fun Mask(modifier: Modifier = Modifier, mask: String) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        val sizeItem = ((maxWidth - 6.dp * (mask.length - 1)) / mask.length)
        val shakeController = rememberShakeController()
        Row(horizontalArrangement = Arrangement.Center) {
            mask.mapIndexed { index, letter ->
                Box(
                    modifier = Modifier
                        .sizeIn(maxWidth = sizeItem)
                        .aspectRatio(1f)
                        .background(color = MaterialTheme.colorScheme.primary)
                        .shake(shakeController),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = letter.toString(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = sizeItem.value.sp * 0.6
                    )
                }
                if (index != mask.lastIndex) {
                    HorizontalSpacer4Dp()
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
