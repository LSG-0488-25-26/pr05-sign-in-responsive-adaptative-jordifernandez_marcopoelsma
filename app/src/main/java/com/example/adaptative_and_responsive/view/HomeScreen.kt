package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.viewModel
@Composable
fun HomeScreen(viewModel: viewModel) {
    val user by viewModel.user.collectAsState()

    val displayName = if (user.fullName.isNotEmpty()) user.fullName else user.username

    Column(modifier = Modifier.fillMaxSize()) {
        AppBanner()
        Spacer(modifier = Modifier.height(32.dp))

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Benvingut/da, $displayName!",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Has iniciat sessió correctament.", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.backToInitial() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tornar")
            }
        }
    }
}
