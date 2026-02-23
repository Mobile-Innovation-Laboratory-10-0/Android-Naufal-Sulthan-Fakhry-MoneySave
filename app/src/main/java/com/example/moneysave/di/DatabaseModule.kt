package com.example.moneysave.di

import androidx.room.Room
import com.example.moneysave.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "money_save_db"
        ).build()
    }

    single { get<AppDatabase>().transactionDao() }
}