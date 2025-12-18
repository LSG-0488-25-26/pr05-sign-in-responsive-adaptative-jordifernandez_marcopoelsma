package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.RegisterViewModel

@Composable
fun LoginScreen(
    viewModel: RegisterViewModel,
    onNavigateToRegister: () -> Unit
) {
    val user by viewModel.user.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AppBanner()

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Inici de sessió", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = user.username,
            onValueChange = viewModel::onUsernameChange,
            label = { Text("Nom d’usuari") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = user.password,
            onValueChange = viewModel::onPasswordChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { if(viewModel.isLoginValid()) viewModel.goToHome() },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Inicia sessió") }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onNavigateToRegister, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("No tens compte? Registra’t")
        }
    }
}
