package com.example.moneysave.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneysave.repository.TransaksiRepository
import com.example.moneysave.data.entity.TransaksiEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TransaksiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getTransaksi().collect { dataList ->
                val income = dataList.filter { it.type == "Income" }.sumOf { it.jumlah }
                val expense = dataList.filter { it.type == "Expense" }.sumOf { it.jumlah }
                val total = income - expense

                _uiState.update {
                    it.copy(
                        transaksi = dataList,
                        totalSaldo = total
                    )
                }
            }
        }
    }

    fun deleteTransaction(transaction: TransaksiEntity) {
        viewModelScope.launch {
            repository.delete(transaction)
        }
    }
}