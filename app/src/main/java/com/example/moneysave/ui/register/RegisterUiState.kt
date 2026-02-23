package com.example.moneysave.ui.register

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)