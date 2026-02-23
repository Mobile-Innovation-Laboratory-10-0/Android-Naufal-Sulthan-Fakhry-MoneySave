package com.example.moneysave.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.moneysave.data.entity.TransaksiEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransaksiDao {
    @Insert
    suspend fun insert(transaksi: TransaksiEntity)

    @Update
    suspend fun update(transaksi: TransaksiEntity)

    @Delete
    suspend fun delete(transaksi: TransaksiEntity)

    @Query("SELECT * FROM transaksi ORDER BY tanggal DESC")
    fun getAllTransaksi(): Flow<List<TransaksiEntity>>
}
