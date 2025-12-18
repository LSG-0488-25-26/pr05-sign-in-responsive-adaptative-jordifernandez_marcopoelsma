package com.example.adaptative_and_responsive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import com.example.adaptative_and_responsive.ui.theme.Adaptative_and_responsiveTheme
import com.example.adaptative_and_responsive.view.HomeScreen
import com.example.adaptative_and_responsive.view.LoginScreen
import com.example.adaptative_and_responsive.view.RegisterScreen
import com.example.adaptative_and_responsive.viewmodel.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: viewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Adaptative_and_responsiveTheme {
                val showHome by viewModel.showHome.collectAsState()
                var showRegister by remember { mutableStateOf(false) }

                when {
                    showHome -> HomeScreen(viewModel = viewModel)
                    showRegister -> RegisterScreen(
                        viewModel = viewModel,
                        onBackToLogin = { showRegister = false }
                    )
                    else -> LoginScreen(
                        viewModel = viewModel,
                        onNavigateToRegister = { showRegister = true }
                    )
                }
            }
        }
    }
}
