package com.chskela.gallowsgame.presentation.ui.components.topappbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.gallowsgame.R
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GallowsTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {
        Spacer(
            modifier = Modifier
                .padding(start = 16.dp, end = 12.dp)
                .size(48.dp)
        )
    },
    onNavigation: () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {
        IconButton(onClick = onNavigation) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    },
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
        ),
    )
}

@Preview(showBackground = true, name = "Light GallowsTopAppBar")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewGallowsTopAppBar() {
    GallowsGameTheme {
        GallowsTopAppBar(title = stringResource(id = R.string.app_name))
    }
}