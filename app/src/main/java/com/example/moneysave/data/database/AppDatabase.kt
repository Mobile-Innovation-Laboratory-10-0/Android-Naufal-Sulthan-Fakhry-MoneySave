package com.example.moneysave.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moneysave.data.dao.TransaksiDao
import com.example.moneysave.data.entity.TransaksiEntity

@Database(entities = [TransaksiEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransaksiDao
}
