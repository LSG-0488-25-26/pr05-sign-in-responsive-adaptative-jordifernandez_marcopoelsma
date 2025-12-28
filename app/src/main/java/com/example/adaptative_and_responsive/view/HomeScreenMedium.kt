package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
fun HomeScreenMedium(viewModel: viewModel, windowSizeClass: WindowSizeClass) {
    val user by viewModel.user.collectAsState()
    val displayName = if (user.fullName.isNotEmpty()) user.fullName else user.username

    Row(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AppBanner(windowSizeClass)

        Column(modifier = Modifier.weight(1f)) {
            Text("Benvingut/da, $displayName!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Has iniciat sessió correctament.", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { viewModel.backToInitial() }, modifier = Modifier.fillMaxWidth()) {
                Text("Tornar")
            }
        }
    }
}
