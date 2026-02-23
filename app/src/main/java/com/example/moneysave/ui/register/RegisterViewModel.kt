package com.example.moneysave.ui.register

import androidx.lifecycle.ViewModel
import com.example.moneysave.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun changeEmail(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun changePassword(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun register() {
        repository.register(uiState.value.email, uiState.value.password)
            .addOnSuccessListener {
                _uiState.update { it.copy(isSuccess = true) }
            }
            .addOnFailureListener { e ->
                _uiState.update {
                    it.copy(errorMessage = e.message)
                }
            }
    }

    fun clearErrorMessage() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}