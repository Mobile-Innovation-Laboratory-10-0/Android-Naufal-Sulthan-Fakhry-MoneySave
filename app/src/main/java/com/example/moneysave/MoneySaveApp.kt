package com.example.moneysave

import android.app.Application
import com.example.moneysave.di.databaseModule
import com.example.moneysave.di.firebaseModule
import com.example.moneysave.di.repositoryModule
import com.example.moneysave.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module

class MoneySaveApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoneySaveApp)
            modules(
                firebaseModule,
                databaseModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}