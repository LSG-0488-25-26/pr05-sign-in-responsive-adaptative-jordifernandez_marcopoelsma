package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppBanner(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier
) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> AppBannerCompact()
        WindowWidthSizeClass.Medium -> AppBannerMedium()
        WindowWidthSizeClass.Expanded -> AppBannerExpanded()
    }
}


