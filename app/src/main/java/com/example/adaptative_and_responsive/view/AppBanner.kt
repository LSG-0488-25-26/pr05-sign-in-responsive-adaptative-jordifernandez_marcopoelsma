package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppBanner() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*
        Icon(

            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
        */


        Text(
            text = "GymPro",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Entrena millor cada dia",
            color = Color.White
        )
    }
}
