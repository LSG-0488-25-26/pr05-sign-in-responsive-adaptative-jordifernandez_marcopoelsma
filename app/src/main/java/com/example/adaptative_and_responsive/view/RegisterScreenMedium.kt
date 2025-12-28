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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.viewModel
@Composable
fun RegisterScreenMedium(
    viewModel: viewModel,
    isLandscape: Boolean,
    onBackToLogin: () -> Unit
) {
    val user by viewModel.user.collectAsState()
    val birthDateValue by viewModel.birthDateValue.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    AppBanner()

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(modifier = Modifier.width(360.dp)) {
                Text("Registre", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(16.dp))
                RegisterForm(viewModel, user, birthDateValue, error)
            }

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                RegisterActions(viewModel, onBackToLogin)
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text("Registre", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(24.dp))
            RegisterForm(viewModel, user, birthDateValue, error)
            Spacer(Modifier.height(24.dp))
            RegisterActions(viewModel, onBackToLogin)
        }
    }
}
