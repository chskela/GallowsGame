package com.chskela.gallowsgame.utils

import androidx.compose.ui.unit.DpSize
import kotlin.math.ceil
import kotlin.math.sqrt

fun calculateSizeItem(windowInfo: WindowInfo, size: Int): DpSize {
    val screenHeight = windowInfo.screenHeight / 2
    val screenWidth = windowInfo.screenWidth
    val proportion = screenWidth / screenHeight
    val sqrt = sqrt(size / proportion)
    val countRow = (if (sqrt == ceil(sqrt)) sqrt else sqrt + 1).toInt() + 2
    val height = screenHeight / countRow
    val width = screenWidth / (countRow * proportion)
    return DpSize(height, width)
}