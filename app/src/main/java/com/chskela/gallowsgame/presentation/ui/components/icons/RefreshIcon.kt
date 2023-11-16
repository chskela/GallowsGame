package com.chskela.gallowsgame.presentation.ui.components.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.chskela.gallowsgame.R

@Composable
fun RefreshIcon() {
    Icon(
        imageVector = Icons.Rounded.Refresh,
        contentDescription = stringResource(id = R.string.back),
        tint = MaterialTheme.colorScheme.secondary
    )
}