package com.example.moneysave.repository

import com.example.moneysave.data.dao.TransaksiDao
import com.example.moneysave.data.entity.TransaksiEntity

class TransaksiRepository(
    private val dao: TransaksiDao
) {

    fun getTransaksi() = dao.getAllTransaksi()

    suspend fun add(title: String, amount: Double, type: String) {
        dao.insert(
            TransaksiEntity(
                judul = title,
                jumlah = amount,
                type = type,
                tanggal = System.currentTimeMillis()
            )
        )
    }

    suspend fun delete(transaction: TransaksiEntity) {
        dao.delete(transaction)
    }
    suspend fun update(transaction: TransaksiEntity) {
        dao.update(transaction)
    }
}