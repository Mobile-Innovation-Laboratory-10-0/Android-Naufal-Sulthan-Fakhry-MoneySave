package com.example.moneysave.ui.home

import com.example.moneysave.data.entity.TransaksiEntity

data class HomeUiState(
    val transaksi: List<TransaksiEntity> = emptyList(),
    val totalSaldo: Double = 0.0
)