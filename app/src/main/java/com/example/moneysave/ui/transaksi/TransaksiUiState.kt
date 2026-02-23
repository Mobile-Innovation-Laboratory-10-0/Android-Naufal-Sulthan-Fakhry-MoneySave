package com.example.moneysave.ui.transaksi


data class AddTransactionUiState(
    val judul: String = "",
    val jumlah: String = "",
    val type: String = "Income",
    val isSuccess: Boolean = false
)