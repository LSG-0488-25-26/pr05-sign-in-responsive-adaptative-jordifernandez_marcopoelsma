package com.example.adaptative_and_responsive.view

import android.content.res.Configuration
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import com.example.adaptative_and_responsive.viewmodel.viewModel

@Composable
fun LoginScreen(
    viewModel: viewModel,
    windowSizeClass: WindowSizeClass,
    onNavigateToRegister: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact ->
            LoginScreenCompact(viewModel, windowSizeClass, onNavigateToRegister)

        WindowWidthSizeClass.Medium ->
            LoginScreenMedium(viewModel, isLandscape, windowSizeClass, onNavigateToRegister)

        WindowWidthSizeClass.Expanded ->
            LoginScreenExpanded(viewModel, onNavigateToRegister, windowSizeClass)
    }
}

