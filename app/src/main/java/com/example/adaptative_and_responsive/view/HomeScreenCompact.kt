package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.RegisterViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeScreenCompact(viewModel: RegisterViewModel) {
    val user by viewModel.user.collectAsState()
    val displayName = if (user.fullName.isNotEmpty()) user.fullName else user.username

    Column(modifier = Modifier.fillMaxSize()) {
        AppBanner(windowSizeClass = WindowSizeClass.calculateFromSize(android.util.Size(0, 0)),)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Benvingut/da, $displayName!",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Has iniciat sessió correctament.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = { viewModel.backToInitial() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tornar")
            }
        }
    }
}
