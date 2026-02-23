package com.example.moneysave.di

import com.example.moneysave.ui.home.HomeViewModel
import com.example.moneysave.ui.login.LoginViewModel
import com.example.moneysave.ui.register.RegisterViewModel
import com.example.moneysave.ui.transaksi.AddTransaksiViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { AddTransaksiViewModel(get()) }
}