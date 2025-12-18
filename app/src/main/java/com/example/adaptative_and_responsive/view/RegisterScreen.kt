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
import androidx.compose.ui.text.input.TextFieldValue
import com.example.adaptative_and_responsive.viewmodel.viewModel

@Composable
fun RegisterScreen(
    viewModel: viewModel,
    onBackToLogin: () -> Unit
) {
    val user by viewModel.user.collectAsState()
    val birthDate by viewModel.birthDateValue.collectAsState()
    val error by viewModel.errorMessage.collectAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AppBanner()

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Registre", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = user.fullName,
            onValueChange = viewModel::onFullNameChange,
            label = { Text("Nom complet") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = birthDate,
            onValueChange = viewModel::onBirthDateChange,
            label = { Text("Data de naixement (dd/mm/yyyy)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = user.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = user.phone,
            onValueChange = viewModel::onPhoneChange,
            label = { Text("Telèfon") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = user.username,
            onValueChange = viewModel::onUsernameChange,
            label = { Text("Nom d’usuari") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = user.password,
            onValueChange = viewModel::onPasswordChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = user.confirmPassword,
            onValueChange = viewModel::onConfirmPasswordChange,
            label = { Text("Confirma Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = user.termsAccepted,
                onCheckedChange = viewModel::onTermsAcceptedChange
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Accepto els termes i condicions")
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = { if (viewModel.isRegisterValid()) viewModel.goToHome() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar-se")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = onBackToLogin,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Ja tens compte? Inicia sessió")
        }
    }
}
