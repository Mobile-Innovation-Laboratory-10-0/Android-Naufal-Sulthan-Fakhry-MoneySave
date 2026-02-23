package com.example.moneysave.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaksi")
data class TransaksiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val jumlah: Double,
    val type: String,
    val tanggal: Long
)
