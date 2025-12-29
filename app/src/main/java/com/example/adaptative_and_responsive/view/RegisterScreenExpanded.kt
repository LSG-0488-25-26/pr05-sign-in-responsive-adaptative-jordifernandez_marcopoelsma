package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
fun RegisterScreenExpanded(
    viewModel: viewModel,
    windowSizeClass: WindowSizeClass,
    onBackToLogin: () -> Unit
) {
    val user by viewModel.user.collectAsState()
    val birthDateValue by viewModel.birthDateValue.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    Row(modifier = Modifier.fillMaxSize()) {
        AppBanner(
            windowSizeClass = windowSizeClass,
            modifier = Modifier
                .fillMaxHeight()
                .width(320.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Registre", style = MaterialTheme.typography.headlineLarge)

            Spacer(Modifier.height(24.dp))

            RegisterForm(viewModel, user, birthDateValue, error)

            Spacer(Modifier.height(32.dp))

            RegisterActions(viewModel, windowSizeClass, onBackToLogin)
        }
    }
}

