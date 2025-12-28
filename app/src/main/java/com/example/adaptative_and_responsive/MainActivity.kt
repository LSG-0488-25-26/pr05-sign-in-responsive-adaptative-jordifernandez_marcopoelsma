package com.example.adaptative_and_responsive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.adaptative_and_responsive.ui.theme.Adaptative_and_responsiveTheme
import com.example.adaptative_and_responsive.view.HomeScreen
import com.example.adaptative_and_responsive.view.LoginScreen
import com.example.adaptative_and_responsive.view.RegisterScreen
import com.example.adaptative_and_responsive.viewmodel.viewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

    private val viewModel: viewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Adaptative_and_responsiveTheme {

                val context = LocalContext.current
                val windowSizeClass = calculateWindowSizeClass(activity = context as ComponentActivity)

                val showHome by viewModel.showHome.collectAsState()
                var showRegister by remember { mutableStateOf(false) }

                when {
                    showHome -> HomeScreen(
                        viewModel = viewModel,
                        windowSizeClass = windowSizeClass
                    )

                    showRegister -> RegisterScreen(
                        viewModel = viewModel,
                        onBackToLogin = { showRegister = false },
                        windowSizeClass = windowSizeClass
                    )

                    else -> LoginScreen(
                        viewModel = viewModel,
                        onNavigateToRegister = { showRegister = true },
                        windowSizeClass = windowSizeClass
                    )
                }
            }
        }
    }
}
