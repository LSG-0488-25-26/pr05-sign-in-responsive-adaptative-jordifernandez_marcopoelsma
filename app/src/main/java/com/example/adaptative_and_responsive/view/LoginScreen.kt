package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.viewModel

@Composable
fun LoginScreen(
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
        // Banner superior
        AppBanner()

        Spacer(modifier = Modifier.height(32.dp))

        // Título
        Text(
            text = "Inici de sessió",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de nombre de usuario
        OutlinedTextField(
            value = user.username,
            onValueChange = viewModel::onUsernameChange,
            label = { Text("Nom d’usuari") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        // Campo de password
        OutlinedTextField(
            value = user.password,
            onValueChange = viewModel::onPasswordChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar mensaje de error
        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Botón de iniciar sesión
        Button(
            onClick = {
                if (viewModel.isLoginValid(user.username, user.password)) {
                    viewModel.goToHome()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Inicia sessió")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para ir a registro
        TextButton(
            onClick = onNavigateToRegister,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("No tens compte? Registra’t")
        }
    }
}
