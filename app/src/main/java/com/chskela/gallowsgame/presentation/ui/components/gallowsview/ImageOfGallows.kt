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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme

@Composable
fun ImageOfGallows(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val color = Color.Black

        val height = size.height
        val baseWidth = height * 0.8f
        val topOfPost = height * 0.1f

        val strokeWidthBase = 30f
        val strokeWidthPost = 20f
        val strokeWidthCrossbar = 20f
        val strokeWidthRope = 7f
        val strokeWidthHands = 5f
        val strokeWidthLegs = 10f

        val lengthRope = baseWidth * 0.2f
        val lengthHands = baseWidth * 0.2f
        val lengthLegs = baseWidth * 0.25f

        val paddingRope = 20f
        val radiusHead = baseWidth * 0.07f
        val sizeOval = Size(radiusHead * 2.5f, radiusHead * 5)
        val paddingLegs = sizeOval.width / 3

        // Base
        drawLine(
            color = color,
            start = Offset(center.x - baseWidth / 2, height - strokeWidthBase),
            end = Offset(center.x + baseWidth / 2, height - strokeWidthBase),
            strokeWidth = strokeWidthBase
        )
        //Post
        drawLine(
            color = color,
            start = Offset(center.x - baseWidth / 4, height - strokeWidthBase),
            end = Offset(center.x - baseWidth / 4, topOfPost),
            strokeWidth = strokeWidthPost
        )

        // Crossbar
        drawLine(
            color = color,
            start = Offset(center.x - baseWidth / 4, topOfPost + strokeWidthCrossbar / 2),
            end = Offset(center.x + baseWidth / 4, topOfPost + strokeWidthCrossbar / 2),
            strokeWidth = strokeWidthCrossbar
        )

        // Rope
        drawLine(
            color = color,
            start = Offset(center.x + baseWidth / 4 - paddingRope, topOfPost - strokeWidthRope),
            end = Offset(center.x + baseWidth / 4 - paddingRope, topOfPost + lengthRope),
            strokeWidth = strokeWidthRope
        )

        // Head
        drawCircle(
            color = color,
            radius = radiusHead,
            center = Offset(
                center.x + baseWidth / 4 - paddingRope,
                topOfPost + lengthRope + radiusHead
            ),
            style = Stroke(5f)
        )

        // Body
        drawOval(
            color = color, topLeft = Offset(
                center.x + baseWidth / 4 - paddingRope - sizeOval.width / 2,
                topOfPost + lengthRope + radiusHead * 2
            ), size = sizeOval
        )

        // Hands
        drawLine(
            color = color,
            start = Offset(
                center.x + baseWidth / 4 - paddingRope,
                topOfPost + lengthRope + radiusHead * 2
            ),
            end = Offset(
                center.x + baseWidth / 4 - paddingRope - sizeOval.width,
                topOfPost + lengthRope + radiusHead * 2 + lengthHands
            ),
            strokeWidth = strokeWidthHands
        )
        drawLine(
            color = color,
            start = Offset(
                center.x + baseWidth / 4 - paddingRope,
                topOfPost + lengthRope + radiusHead * 2
            ),
            end = Offset(
                center.x + baseWidth / 4 - paddingRope + sizeOval.width,
                topOfPost + lengthRope + radiusHead * 2 + lengthHands
            ),
            strokeWidth = strokeWidthHands
        )

        // Legs

        drawLine(
            color = color,
            start = Offset(
                center.x + baseWidth / 4 - paddingRope - paddingLegs,
                topOfPost + lengthRope + radiusHead + sizeOval.height
            ),
            end = Offset(
                center.x + baseWidth / 4 - paddingRope - paddingLegs,
                topOfPost + lengthRope + radiusHead + sizeOval.height + lengthLegs
            ),
            strokeWidth = strokeWidthLegs
        )
        drawLine(
            color = color,
            start = Offset(
                center.x + baseWidth / 4 - paddingRope + paddingLegs,
                topOfPost + lengthRope + radiusHead + sizeOval.height
            ),
            end = Offset(
                center.x + baseWidth / 4 - paddingRope + paddingLegs,
                topOfPost + lengthRope + radiusHead + sizeOval.height + lengthLegs
            ),
            strokeWidth = strokeWidthLegs
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewKeyboardGrid() {
    GallowsGameTheme {
        Box(Modifier.size(400.dp)) {
            ImageOfGallows()
        }

    }
}