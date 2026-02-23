package com.example.moneysave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moneysave.ui.navigation.AppNavHost
import com.example.moneysave.ui.theme.MoneySaveTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoneySaveTheme {
                AppNavHost()
            }
        }
    }
}