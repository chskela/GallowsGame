package com.chskela.gallowsgame.presentation.ui.components.gallowsview

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme
import kotlin.math.sqrt

@Composable
fun ImageOfGallows(modifier: Modifier = Modifier, stage: Int) {
    val color = MaterialTheme.colorScheme.onBackground
    val woodColor = Color(0xffe0803f)
    val ropeColor = Color(0xffcaa185)
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val height = size.height

        val widthBase = height * 0.8f
        val heightBase = widthBase * 0.07f
        val paddingYBase = height - heightBase

        val heightPost = height * 0.9f
        val widthPost = heightBase * 0.8f
        val paddingXPost = center.x - widthBase / 4
        val paddingYPost = paddingYBase - heightPost

        val heightCrossbar = heightBase * 0.7f
        val widthCrossbar = widthBase / 2 + heightCrossbar

        val widthRope = heightCrossbar * 0.3f
        val lengthRope = widthBase * 0.2f
        val paddingRope = widthRope * 4
        val paddingXRope = center.x + widthBase / 4 - paddingRope
        val topLeftRope = Offset(x = paddingXRope, y = paddingYPost - widthRope)

        val radiusHead = widthBase * 0.07f
        val paddingYHead = paddingYPost - widthRope + lengthRope + radiusHead
        val paddingXHead = paddingXRope + widthRope / 2
        val sizeOval = Size(width = radiusHead * 2.5f, height = radiusHead * 5)

        val paddingYBody = paddingYPost - widthRope + lengthRope + radiusHead * 2
        val paddingXBody = paddingXHead - sizeOval.width / 2

        val strokeWidthHands = radiusHead * 0.2f
        val lengthHands = radiusHead * 3f

        val strokeWidthLegs = radiusHead * 0.3f
        val lengthLegs = radiusHead * 3.5f
        val offsetLegs = sizeOval.width / 3
        val paddingXLegs = paddingXHead - offsetLegs
        val paddingYLegs = paddingYHead + sizeOval.height

        // Base
        if (stage >= 1) {
            drawRectWithStroke(
                fillColor = woodColor,
                strokeColor = color,
                topLeft = Offset(x = center.x - widthBase / 2, y = paddingYBase),
                size = Size(width = widthBase, height = heightBase),
            )
        }

        if (stage >= 3) {
            rotate(-45F, pivot = Offset(x = paddingXPost, y = paddingYPost + widthCrossbar / 2)) {
                drawRectWithStroke(
                    fillColor = woodColor,
                    strokeColor = color,
                    topLeft = Offset(x = paddingXPost, y = paddingYPost + widthCrossbar / 2),
                    size = Size(
                        width = sqrt(widthCrossbar * widthCrossbar / 2),
                        height = heightCrossbar
                    ),
                )
            }
        }

        // Post
        if (stage >= 2) {
            drawRectWithStroke(
                fillColor = woodColor,
                strokeColor = color,
                topLeft = Offset(x = paddingXPost, y = paddingYPost),
                size = Size(width = widthPost, height = heightPost),
            )
        }

        // Crossbar
        if (stage >= 3) {
            drawRectWithStroke(
                fillColor = woodColor,
                strokeColor = color,
                topLeft = Offset(x = paddingXPost - heightCrossbar, y = paddingYPost),
                size = Size(width = widthCrossbar, height = heightCrossbar),
            )
        }

        // Rope
        if (stage >= 4) {
            drawRectWithStroke(
                fillColor = ropeColor,
                strokeColor = color,
                topLeft = topLeftRope.copy(x = topLeftRope.x - widthRope),
                size = Size(width = widthRope, height = heightCrossbar + widthRope * 2),
            )
            drawRectWithStroke(
                fillColor = ropeColor,
                strokeColor = color,
                topLeft = topLeftRope,
                size = Size(width = widthRope, height = lengthRope),
            )
            drawRectWithStroke(
                fillColor = ropeColor,
                strokeColor = color,
                topLeft = topLeftRope.copy(x = topLeftRope.x + widthRope),
                size = Size(width = widthRope, height = heightCrossbar + widthRope * 2),
            )
        }

        // Head
        if (stage >= 5) {
            drawCircle(
                color = color,
                radius = radiusHead,
                center = Offset(x = paddingXHead, y = paddingYHead),
                style = Stroke(5f)
            )
        }

        // Body
        if (stage >= 6) {
            drawOval(
                color = color,
                topLeft = Offset(x = paddingXBody, y = paddingYBody),
                size = sizeOval
            )
        }

        // Hands
        if (stage >= 7) {
            drawLine(
                color = color,
                start = Offset(x = paddingXHead, y = paddingYBody),
                end = Offset(x = paddingXRope - sizeOval.width, y = paddingYBody + lengthHands),
                strokeWidth = strokeWidthHands
            )
            drawLine(
                color = color,
                start = Offset(x = paddingXHead, y = paddingYBody),
                end = Offset(x = paddingXRope + sizeOval.width, y = paddingYBody + lengthHands),
                strokeWidth = strokeWidthHands
            )
        }

        // Legs
        if (stage >= 8) {
            val pathLeg = Path()
            pathLeg.moveTo(
                x = paddingXLegs,
                y = paddingYLegs
            )
            pathLeg.lineTo(
                x = paddingXLegs,
                y = paddingYLegs + lengthLegs
            )

            drawPath(path = pathLeg, color = color, style = Stroke(strokeWidthLegs))
            translate(left = offsetLegs * 2) {
                drawPath(path = pathLeg, color = color, style = Stroke(strokeWidthLegs))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewKeyboardGrid() {
    GallowsGameTheme {
        Box(Modifier.size(400.dp)) {
            ImageOfGallows(stage = 8)
        }

    }
}