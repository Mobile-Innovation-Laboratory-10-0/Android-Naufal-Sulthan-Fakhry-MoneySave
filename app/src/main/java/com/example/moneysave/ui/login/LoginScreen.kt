package com.example.moneysave.ui.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moneysave.ui.navigation.Home
import com.example.moneysave.ui.navigation.Register
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(navController: NavController) {

    val viewModel: LoginViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isSuccess) {
        LaunchedEffect(Unit) {
            navController.navigate(Home) {
                popUpTo(0)
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                val primaryColor = MaterialTheme.colorScheme.primary

                Canvas(modifier = Modifier.size(70.dp)) {
                    drawRoundRect(
                        color = primaryColor,
                        size = size.copy(height = size.height * 0.7f),
                        cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
                    )
                    drawCircle(
                        color = Color.White,
                        radius = 6.dp.toPx(),
                        center = center.copy(x = size.width * 0.75f, y = size.height * 0.35f)
                    )
                    drawRoundRect(
                        color = primaryColor.copy(alpha = 0.3f),
                        topLeft = Offset(0f, size.height * 0.8f),
                        size = size.copy(height = size.height * 0.15f),
                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraLight, fontSize = 38.sp, color = Color.Gray)) {
                            append("Money")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Black, fontSize = 38.sp, color = primaryColor)) {
                            append("Save")
                        }
                    }
                )

                Text(
                    text = "Disiplin catat finansial, amankan masa depan.",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray.copy(alpha = 0.7f)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            OutlinedTextField(
                value = uiState.email,
                onValueChange = viewModel::ubahEmail,
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.password,
                onValueChange = viewModel::ubahPassword,
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                enabled = uiState.email.isNotBlank() && uiState.password.isNotBlank()
            ) {
                Text("Masuk Sekarang", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = { navController.navigate(Register) }
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Baru di sini? ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)) {
                            append("Buat Akun")
                        }
                    }
                )
            }
        }
    }

    if (uiState.errorMessage != null) {
        AlertDialog(
            onDismissRequest = viewModel::clearErrorMessage,
            confirmButton = {
                TextButton(onClick = viewModel::clearErrorMessage) { Text("Oke") }
            },
            title = { Text("Gagal Masuk") },
            text = { Text(uiState.errorMessage!!) }
        )
    }
}