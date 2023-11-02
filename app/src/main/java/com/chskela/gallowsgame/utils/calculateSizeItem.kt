package com.chskela.gallowsgame.utils

import androidx.compose.ui.unit.DpSize
import kotlin.math.ceil
import kotlin.math.sqrt

fun calculateSizeItem(windowInfo: WindowInfo, size: Int): DpSize {
    val screenHeight = windowInfo.screenHeight / 2
    val screenWidth = windowInfo.screenWidth
    val proportion = screenWidth / screenHeight
    val countRow = ceil(sqrt(size / proportion)).toInt() + 1
    val height = screenHeight / countRow
    val width = screenWidth / (countRow * proportion)
    return DpSize(height, width)
}