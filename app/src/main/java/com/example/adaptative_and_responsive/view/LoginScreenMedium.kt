package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.viewModel

@Composable
fun LoginScreenMedium(
    viewModel: viewModel,
    isLandscape: Boolean,
    windowSizeClass: WindowSizeClass, // <-- passa la instància aquí
    onNavigateToRegister: () -> Unit
) {
    val user by viewModel.user.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    // Passa la instància correcta
    AppBanner(windowSizeClass = windowSizeClass)

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.width(320.dp)) {
                Text("Inici de sessió", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(16.dp))
                LoginForm(viewModel, user, error)
            }

            LoginActions(viewModel, user, onNavigateToRegister)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Inici de sessió", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(24.dp))
            LoginForm(viewModel, user, error)
            Spacer(Modifier.height(24.dp))
            LoginActions(viewModel, user, onNavigateToRegister)
        }
    }
}

