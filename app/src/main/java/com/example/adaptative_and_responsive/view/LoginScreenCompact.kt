package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.viewModel

@Composable
fun LoginScreenCompact(
    viewModel: viewModel,
    onNavigateToRegister: () -> Unit
) {
    val user by viewModel.user.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AppBanner(
            windowSizeClass = TODO(),
        )

        Spacer(Modifier.height(24.dp))

        Text("Inici de sessió", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        LoginForm(viewModel, user, error)

        Spacer(Modifier.height(16.dp))

        LoginActions(viewModel, user, onNavigateToRegister)
    }
}
