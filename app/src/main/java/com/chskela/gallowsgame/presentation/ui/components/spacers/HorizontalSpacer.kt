package com.chskela.gallowsgame.presentation.ui.components.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width = width))
}

@Composable
fun HorizontalSpacer4Dp() {
    HorizontalSpacer(width = 4.dp)
}