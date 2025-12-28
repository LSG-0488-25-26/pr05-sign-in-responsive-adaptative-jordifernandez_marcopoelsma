package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.model.User
import com.example.adaptative_and_responsive.viewmodel.viewModel


@Composable
fun LoginForm(
    viewModel: viewModel,
    user: User,
    error: String
) {
    OutlinedTextField(
        value = user.username,
        onValueChange = viewModel::onUsernameChange,
        label = { Text("Nom d’usuari") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(12.dp))

    OutlinedTextField(
        value = user.password,
        onValueChange = viewModel::onPasswordChange,
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )

    if (error.isNotEmpty()) {
        Spacer(Modifier.height(8.dp))
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error
        )
    }
}


@Composable
fun LoginActions(
    viewModel: viewModel,
    user: User,
    onNavigateToRegister: () -> Unit
) {
    Button(
        onClick = {
            viewModel.login(user.username, user.password)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Inicia sessió")
    }

    Spacer(Modifier.height(8.dp))

    TextButton(onClick = onNavigateToRegister) {
        Text("No tens compte? Registra’t")
    }
}

