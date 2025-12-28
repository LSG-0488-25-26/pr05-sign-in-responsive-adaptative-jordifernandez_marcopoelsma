package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.viewModel
import androidx.compose.ui.platform.LocalConfiguration
import android.content.res.Configuration
import androidx.compose.material3.windowsizeclass.WindowSizeClass

@Composable
fun HomeScreen(
    viewModel: viewModel,
    windowSizeClass: WindowSizeClass
) {
    val user by viewModel.user.collectAsState()
    val displayName = if (user.fullName.isNotEmpty()) user.fullName else user.username

    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(modifier = Modifier.fillMaxSize()) {
        AppBanner(windowSizeClass,)

        Spacer(modifier = Modifier.height(32.dp))

        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                HomeScreenCompact(viewModel)
            }

            WindowWidthSizeClass.Medium -> {
                HomeScreenMedium(viewModel, isLandscape)
            }

            WindowWidthSizeClass.Expanded -> {
                HomeScreenExpanded(
                    viewModel,
                    isLandscape
                )
            }
        }
    }
}
