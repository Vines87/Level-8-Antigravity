package com.zauberfluff.core.ui

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A helper composable that automatically switches between different layouts
 * based on the provided [WindowSizeClass].
 */
@Composable
fun ZauberfluffAdaptiveLayout(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    portraitLayout: @Composable () -> Unit,
    landscapeLayout: @Composable () -> Unit,
    tabletLayout: @Composable () -> Unit
) {
    val isTabletWidth = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded
    val isLandscape = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

    when {
        isTabletWidth -> {
            tabletLayout()
        }
        isLandscape -> {
            landscapeLayout()
        }
        else -> {
            portraitLayout()
        }
    }
}
