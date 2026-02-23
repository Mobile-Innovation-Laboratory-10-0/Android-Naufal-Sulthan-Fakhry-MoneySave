package com.example.moneysave.ui.transaksi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneysave.data.entity.TransaksiEntity
import com.example.moneysave.repository.TransaksiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTransaksiViewModel(
    private val repository: TransaksiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTransactionUiState())
    val uiState = _uiState.asStateFlow()

    private var transactionId: Int? = null

    fun setupEditData(id: Int, judul: String, jumlah: Double, type: String) {
        transactionId = id
        _uiState.update {
            it.copy(
                judul = judul,
                jumlah = jumlah.toInt().toString(),
                type = type
            )
        }
    }

    fun changejudul(value: String) {
        _uiState.update { it.copy(judul = value) }
    }

    fun changejumlah(value: String) {
        _uiState.update { it.copy(jumlah = value) }
    }

    fun changeType(value: String) {
        _uiState.update { it.copy(type = value) }
    }

    fun saveTransaction() {
        viewModelScope.launch {
            try {
                val parsedAmount = uiState.value.jumlah.toDoubleOrNull() ?: 0.0

                if (transactionId != null) {
                    repository.update(
                        TransaksiEntity(
                            id = transactionId!!,
                            judul = uiState.value.judul,
                            jumlah = parsedAmount,
                            type = uiState.value.type,
                            tanggal = System.currentTimeMillis()
                        )
                    )
                } else {
                    repository.add(
                        title = uiState.value.judul,
                        amount = parsedAmount,
                        type = uiState.value.type
                    )
                }
                _uiState.update { it.copy(isSuccess = true) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}