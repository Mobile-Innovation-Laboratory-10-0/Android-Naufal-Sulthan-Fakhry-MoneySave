package com.example.moneysave.ui.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moneysave.ui.navigation.Login
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreen(navController: NavController) {

    val viewModel: RegisterViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()


    if (uiState.isSuccess) {
        LaunchedEffect(Unit) {
            navController.navigate(Login) {
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
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Register Money Save",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = uiState.email,
                onValueChange = viewModel::changeEmail,
                label = { Text("Email") },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email Icon") // ICON: Email
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = uiState.password,
                onValueChange = viewModel::changePassword,
                label = { Text("Password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Password Icon") // ICON: Lock
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { viewModel.register() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Register")
            }
        }
    }

    uiState.errorMessage?.let {
        AlertDialog(
            onDismissRequest = viewModel::clearErrorMessage,
            confirmButton = {
                TextButton(onClick = viewModel::clearErrorMessage) {
                    Text("Close")
                }
            },
            title = { Text("Error") },
            text = { Text(it) }
        )
    }
}