package com.chskela.gallowsgame.utils

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun getWindowInfo(activity: Activity): WindowInfo {
    val configuration: Configuration = LocalConfiguration.current
    val windowSize = calculateWindowSizeClass(activity = activity)

    return WindowInfo(
        windowWidthSize = windowSize.widthSizeClass,
        windowHeightSize = windowSize.heightSizeClass,
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp
    )
}

data class WindowInfo(
    val windowWidthSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    val windowHeightSize: WindowHeightSizeClass = WindowHeightSizeClass.Medium,
    val screenWidth: Dp = 480.dp,
    val screenHeight: Dp = 840.dp
)