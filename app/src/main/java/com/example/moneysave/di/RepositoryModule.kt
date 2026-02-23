package com.example.moneysave.di

import com.example.moneysave.repository.AuthRepository
import com.example.moneysave.repository.TransaksiRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get()) }
    single { TransaksiRepository(get()) }
}