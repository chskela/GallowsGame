package com.chskela.gallowsgame.presentation.ui.components.keyboard

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme
import kotlin.math.ceil

@Composable
fun KeyboardGrid(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val ratio = constraints.maxWidth / placeables[0].width
        val padding = (constraints.maxWidth - placeables[0].width * ratio) / (ratio - 1)
        layout(
            width = placeables[0].width * ratio + padding * (ratio - 1),
            height = (placeables[0].height + padding) * ceil(placeables.size.toDouble() / ratio).toInt()
        ) {
            var xPosition = 0
            var yPosition = 0
            placeables.forEachIndexed { index, placeable ->
                placeable.place(
                    x = xPosition,
                    y = yPosition
                )
                val isLastInRow = (index + 1) % ratio == 0
                xPosition = if (!isLastInRow) xPosition + placeable.width + padding else 0
                yPosition = if (isLastInRow) yPosition + placeable.height + padding else yPosition
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewCategoryGrid() {
    GallowsGameTheme {
        KeyboardGrid {
            listOf(
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
                Key(letter = 'F', enabled = true),
            )
        }
    }
}