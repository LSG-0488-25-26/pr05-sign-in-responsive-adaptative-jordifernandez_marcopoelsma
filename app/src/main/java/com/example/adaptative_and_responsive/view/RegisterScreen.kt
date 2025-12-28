package com.example.adaptative_and_responsive.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import com.example.adaptative_and_responsive.viewmodel.viewModel

@Composable
fun RegisterScreen(
    viewModel: viewModel,
    windowSizeClass: WindowSizeClass,
    onBackToLogin: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact ->
            RegisterScreenCompact(viewModel, windowSizeClass, onBackToLogin)

        WindowWidthSizeClass.Medium ->
            RegisterScreenMedium(viewModel, isLandscape, windowSizeClass, onBackToLogin)

        WindowWidthSizeClass.Expanded ->
            RegisterScreenExpanded(viewModel, windowSizeClass, onBackToLogin)
    }
}

