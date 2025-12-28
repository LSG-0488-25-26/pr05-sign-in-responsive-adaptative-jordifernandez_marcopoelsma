package com.example.adaptative_and_responsive.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
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
            RegisterScreenCompact(viewModel, onBackToLogin)

        WindowWidthSizeClass.Medium ->
            RegisterScreenMedium(viewModel, isLandscape, onBackToLogin)

        WindowWidthSizeClass.Expanded ->
            RegisterScreenExpanded(viewModel, onBackToLogin)
    }
}

