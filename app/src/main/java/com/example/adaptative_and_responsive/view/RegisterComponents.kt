package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.model.User
import com.example.adaptative_and_responsive.viewmodel.viewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun RegisterForm(
    viewModel: viewModel,
    user: User,
    birthDateValue: TextFieldValue,
    error: String
) {
    OutlinedTextField(
        value = user.fullName,
        onValueChange = viewModel::onFullNameChange,
        label = { Text("Nom complet") },
        modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
        value = birthDateValue,
        onValueChange = viewModel::onBirthDateChange,
        label = { Text("Data de naixement (dd/mm/yyyy)") },
        modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
        value = user.email,
        onValueChange = viewModel::onEmailChange,
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
        value = user.phone,
        onValueChange = viewModel::onPhoneChange,
        label = { Text("Telèfon") },
        modifier = Modifier.fillMaxWidth()
    )

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

    OutlinedTextField(
        value = user.confirmPassword,
        onValueChange = viewModel::onConfirmPasswordChange,
        label = { Text("Confirma Password") },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(8.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = user.termsAccepted,
            onCheckedChange = viewModel::onTermsAcceptedChange
        )
        Spacer(Modifier.width(8.dp))
        Text("Accepto els termes i condicions")
    }

    if (error.isNotEmpty()) {
        Spacer(Modifier.height(8.dp))
        Text(error, color = MaterialTheme.colorScheme.error)
    }
}
