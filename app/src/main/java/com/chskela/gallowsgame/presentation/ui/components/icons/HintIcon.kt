package com.chskela.gallowsgame.presentation.ui.components.icons

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.chskela.gallowsgame.R

@Composable
fun HintIcon() {
    Icon(
        painter = painterResource(id = R.drawable.hint),
        contentDescription = stringResource(id = R.string.hint),
        tint = MaterialTheme.colorScheme.secondary
    )
}