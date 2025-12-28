package com.example.adaptative_and_responsive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import com.example.adaptative_and_responsive.ui.theme.Adaptative_and_responsiveTheme
import com.example.adaptative_and_responsive.view.HomeScreen
import com.example.adaptative_and_responsive.view.LoginScreen
import com.example.adaptative_and_responsive.view.RegisterScreen
import com.example.adaptative_and_responsive.viewmodel.RegisterViewModel
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration


class MainActivity : ComponentActivity() {

    private val registerViewModel: RegisterViewModel by viewModels()


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Adaptative_and_responsiveTheme {

                val windowSize = calculateWindowSizeClass(this)

                val showHome by registerViewModel.showHome.collectAsState()
                var showRegister by remember { mutableStateOf(false) }

                val configuration = LocalConfiguration.current
                val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

                when {
                    showHome -> HomeScreen(
                        viewModel = registerViewModel,
                        windowSize = windowSize,
                        isLandscape = isLandscape
                    )
                    showRegister -> RegisterScreen(
                        viewModel = registerViewModel,
                        windowSize = windowSize,
                        isLandscape = isLandscape,
                        onBackToLogin = { showRegister = false }
                    )
                    else -> LoginScreen(
                        viewModel = registerViewModel,
                        windowSize = windowSize,
                        isLandscape = isLandscape,
                        onNavigateToRegister = { showRegister = true }
                    )
                }

            }
        }

    }
}
