package com.chskela.gallowsgame.presentation.ui.components.gallowsview

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke


fun DrawScope.drawRectWithStroke(
    fillColor: Color,
    strokeColor: Color,
    topLeft: Offset,
    size: Size
) {
    drawRect(
        color = fillColor,
        topLeft = topLeft,
        size = size,
        style = Fill
    )
    drawRect(
        color = strokeColor,
        topLeft = topLeft,
        size = size,
        style = Stroke(4f)
    )
}