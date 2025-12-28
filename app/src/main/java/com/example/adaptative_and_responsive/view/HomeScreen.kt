package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.RegisterViewModel
@Composable
fun HomeScreen(
    viewModel: RegisterViewModel,
    windowSize: WindowSizeClass,
    isLandscape: Boolean
) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact ->
            HomeScreenCompact(viewModel)

        WindowWidthSizeClass.Medium ->
            HomeScreenMedium(viewModel, isLandscape)

        WindowWidthSizeClass.Expanded ->
            HomeScreenExpanded(viewModel)
    }
}