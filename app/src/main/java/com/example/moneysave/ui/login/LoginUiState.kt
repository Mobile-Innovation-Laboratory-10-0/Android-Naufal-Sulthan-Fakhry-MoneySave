package com.example.moneysave.ui.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)