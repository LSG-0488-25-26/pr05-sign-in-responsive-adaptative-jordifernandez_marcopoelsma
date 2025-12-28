package com.example.adaptative_and_responsive.view

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.example.adaptative_and_responsive.viewmodel.viewModel
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun HomeScreen(viewModel: viewModel, windowSizeClass: WindowSizeClass) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> HomeScreenCompact(viewModel, windowSizeClass)
        WindowWidthSizeClass.Medium -> HomeScreenMedium(viewModel, windowSizeClass)
        WindowWidthSizeClass.Expanded -> HomeScreenExpanded(viewModel, windowSizeClass)
        else -> HomeScreenCompact(viewModel, windowSizeClass) // fallback
    }
}
