package com.chskela.gallowsgame.presentation.ui.components.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height = height))
}

@Composable
fun VerticalSpacer8Dp() {
    VerticalSpacer(height = 8.dp)
}
