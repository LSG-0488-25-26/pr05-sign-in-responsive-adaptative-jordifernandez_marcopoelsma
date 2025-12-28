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
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.viewModel
import androidx.compose.ui.unit.DpSize

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeScreenExpanded(viewModel: viewModel, windowSizeClass: WindowSizeClass) {
    val user by viewModel.user.collectAsState()
    val displayName = if (user.fullName.isNotEmpty()) user.fullName else user.username

    Row(modifier = Modifier
        .fillMaxSize()
        .padding(32.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        AppBanner(windowSizeClass)

        Column(modifier = Modifier.weight(1f)) {
            Text("Benvingut/da, $displayName!", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(24.dp))
            Text("Has iniciat sessió correctament.", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { viewModel.backToInitial() }, modifier = Modifier.fillMaxWidth()) {
                Text("Tornar")
            }
        }
    }
}
