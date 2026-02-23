package com.example.moneysave.ui.login

import androidx.lifecycle.ViewModel
import com.example.moneysave.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    private val repo: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun ubahEmail(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun ubahPassword(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun login() {
        repo.login(uiState.value.email, uiState.value.password)
            .addOnSuccessListener {
                _uiState.update { it.copy(isSuccess = true) }
            }
            .addOnFailureListener { exception ->
                _uiState.update { state ->
                    state.copy(
                        errorMessage = exception.message ?: "Login gagal"
                    )
                }
            }
    }

    fun clearErrorMessage() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}