package com.chskela.gallowsgame.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import com.chskela.gallowsgame.presentation.screens.main.MainScreen
import com.chskela.gallowsgame.presentation.screens.main.MainScreenViewModel
import com.chskela.gallowsgame.presentation.ui.theme.GallowsGameTheme
import com.chskela.gallowsgame.utils.DevicePosture
import com.chskela.gallowsgame.utils.getWindowInfo
import com.chskela.gallowsgame.utils.isBookPosture
import com.chskela.gallowsgame.utils.isSeparating
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val devicePostureFlow = WindowInfoTracker.getOrCreate(this)
            .windowLayoutInfo(this)
            .flowWithLifecycle(this.lifecycle)
            .map { layoutInfo ->
                val foldingFeature =
                    layoutInfo.displayFeatures
                        .filterIsInstance<FoldingFeature>()
                        .firstOrNull()
                when {
                    isBookPosture(foldingFeature) ->
                        DevicePosture.BookPosture(foldingFeature.bounds)

                    isSeparating(foldingFeature) ->
                        DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

                    else -> DevicePosture.NormalPosture
                }
            }.stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = DevicePosture.NormalPosture
            )

        setContent {
            val mainScreenViewModel: MainScreenViewModel by viewModels()
            val uiState by mainScreenViewModel.uiState.collectAsState()
            val devicePosture by devicePostureFlow.collectAsState()
            val windowInfo = getWindowInfo(activity = this)

            GallowsGameTheme {
                MainScreen(
                    uiState = uiState,
                    onEvent = mainScreenViewModel::onEvent,
                    windowInfo = windowInfo
                )
            }
        }
    }
}
