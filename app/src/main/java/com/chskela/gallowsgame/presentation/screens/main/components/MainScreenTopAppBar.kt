package com.chskela.gallowsgame.presentation.screens.main.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chskela.gallowsgame.R
import com.chskela.gallowsgame.presentation.screens.main.MainScreenEvent
import com.chskela.gallowsgame.presentation.ui.components.icons.HintIcon
import com.chskela.gallowsgame.presentation.ui.components.icons.RefreshIcon
import com.chskela.gallowsgame.presentation.ui.components.topappbar.GallowsTopAppBar

@Composable
fun MainScreenTopAppBar(
    isHintEnable: Boolean = true,
    onEvent: (MainScreenEvent) -> Unit = {},
) {
    GallowsTopAppBar(
        title = stringResource(id = R.string.app_name),
        navigationIcon = {
            Spacer(modifier = Modifier.size(48.dp))
        },
        actions = {
            IconButton(
                onClick = { onEvent(MainScreenEvent.Hint) },
                enabled = isHintEnable,
                content = { HintIcon() }
            )
            IconButton(
                onClick = { onEvent(MainScreenEvent.NewGame) },
                content = { RefreshIcon() }
            )
        })
}