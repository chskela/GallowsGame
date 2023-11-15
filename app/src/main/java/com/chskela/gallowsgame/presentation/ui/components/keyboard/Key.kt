package com.chskela.gallowsgame.presentation.ui.components.keyboard

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme
import com.chskela.gallowsgame.utils.animation.shake.ShakeConfig
import com.chskela.gallowsgame.utils.animation.shake.rememberShakeController
import com.chskela.gallowsgame.utils.animation.shake.shake

@Composable
fun Key(
    modifier: Modifier = Modifier,
    letter: Char,
    isCorrectLetter: Boolean,
    enabled: Boolean,
    size: DpSize = DpSize(48.dp, 32.dp),
    onClick: () -> Unit = {}
) {
    val red = Color(0xFFDD5D5D)
    val green = Color(0xFF79DD5D)
    val shakeController = rememberShakeController()
    var keyState: KeyState by remember { mutableStateOf(KeyState.Default) }
    keyState = when {
        isCorrectLetter -> KeyState.Correct
        !isCorrectLetter -> KeyState.Wrong
        else -> KeyState.Default
    }
    val letterColor: Color by animateColorAsState(
        when {
            !enabled && keyState is KeyState.Correct -> green
            !enabled && keyState is KeyState.Wrong -> red
            else -> Color.Unspecified
        }, label = "Button color"
    )
    AssistChip(
        modifier = modifier
            .size(
                width = size.width,
                height = size.height,
            )
            .shake(shakeController),
        enabled = enabled,
        onClick = {
            onClick()
            when (keyState) {
                KeyState.Correct -> shakeController.shake(
                    ShakeConfig(
                        iterations = 4,
                        intensity = 1_000f,
                        rotateX = -10f,
                        translateY = 10f,
                    )
                )

                KeyState.Default -> {}
                KeyState.Wrong -> shakeController.shake(
                    ShakeConfig(
                        iterations = 4,
                        intensity = 2_000f,
                        rotateY = 15f,
                        translateX = 20f,
                    )
                )
            }
        },
        label = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset((-4).dp),
                text = letter.toString(),
                color = letterColor,
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
        Key(letter = 'А', enabled = true, isCorrectLetter = true)
    }
}
