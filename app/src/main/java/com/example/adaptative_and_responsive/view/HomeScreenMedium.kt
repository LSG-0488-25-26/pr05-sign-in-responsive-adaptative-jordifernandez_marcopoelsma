package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun HomeScreenExpanded(
    viewModel: RegisterViewModel,
    isLandscape: Boolean
) {
    val user by viewModel.user.collectAsState()
    val displayName = if (user.fullName.isNotEmpty()) user.fullName else user.username

    Column(modifier = Modifier.fillMaxSize()) {
        AppBanner(windowSizeClass = WindowSizeClass.calculateFromSize(android.util.Size(0, 0)))

        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Benvingut/da, $displayName!",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(Modifier.height(12.dp))
                    Text("Has iniciat sessió correctament.")
                }

                Button(
                    onClick = { viewModel.backToInitial() },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("Tornar")
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Benvingut/da, $displayName!",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(Modifier.height(16.dp))
                Text("Has iniciat sessió correctament.")
                Spacer(Modifier.height(32.dp))
                Button(
                    onClick = { viewModel.backToInitial() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tornar")
                }
            }
        }
    }
}
