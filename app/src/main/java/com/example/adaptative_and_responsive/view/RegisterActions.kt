package com.example.adaptative_and_responsive.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adaptative_and_responsive.viewmodel.viewModel
import android.os.Build
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp



@Composable
fun RegisterActions(
    viewModel: viewModel,
    windowSizeClass: WindowSizeClass,
    onBackToLogin: () -> Unit
) {
    when (windowSizeClass.widthSizeClass) {

        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if (viewModel.isRegisterValid()) {
                                viewModel.goToHome()
                            }
                        }
                    }
                ) {
                    Text("Crear compte")
                }

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onBackToLogin
                ) {
                    Text("Tornar")
                }
            }
        }

        WindowWidthSizeClass.Medium -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.width(320.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                if (viewModel.isRegisterValid()) {
                                    viewModel.goToHome()
                                }
                            }
                        }
                    ) {
                        Text("Crear compte")
                    }

                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onBackToLogin
                    ) {
                        Text("Tornar")
                    }
                }
            }
        }

        WindowWidthSizeClass.Expanded -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = onBackToLogin
                ) {
                    Text("Tornar")
                }

                Spacer(Modifier.width(16.dp))

                Button(
                    onClick = {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if (viewModel.isRegisterValid()) {
                                viewModel.goToHome()
                            }
                        }
                    }
                ) {
                    Text("Crear compte")
                }
            }
        }
    }
}

