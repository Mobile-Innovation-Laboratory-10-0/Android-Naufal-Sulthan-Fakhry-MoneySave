package com.example.moneysave.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moneysave.ui.home.HomeScreen
import com.example.moneysave.ui.login.LoginScreen
import com.example.moneysave.ui.register.RegisterScreen
import com.example.moneysave.ui.transaksi.AddTransactionScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {

        composable<Login> {
            LoginScreen(navController)
        }

        composable<Register> {
            RegisterScreen(navController)
        }

        composable<Home> {
            HomeScreen(navController)
        }

        composable<AddTransaction> {
            AddTransactionScreen(navController)
        }


        composable(
            route = "edit/{id}/{judul}/{jumlah}/{type}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("judul") { type = NavType.StringType },
                navArgument("jumlah") { type = NavType.FloatType },
                navArgument("type") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            val judul = backStackEntry.arguments?.getString("judul")
            val jumlah = backStackEntry.arguments?.getFloat("jumlah")?.toDouble()
            val type = backStackEntry.arguments?.getString("type")

            AddTransactionScreen(
                navController = navController,
                id = id,
                judul = judul,
                jumlah = jumlah,
                type = type
            )
        }
    }
}