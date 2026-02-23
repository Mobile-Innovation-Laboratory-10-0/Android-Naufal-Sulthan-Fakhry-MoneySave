package com.example.moneysave.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moneysave.R
import com.example.moneysave.ui.navigation.AddTransaction
import com.example.moneysave.ui.navigation.Login
import org.koin.compose.viewmodel.koinViewModel
import java.text.NumberFormat
import java.util.Locale

fun formatRupiah(number: Double): String {
    val formatter = NumberFormat.getNumberInstance(Locale("id", "ID"))
    return formatter.format(number)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "MoneySave",
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Login) { popUpTo(0) }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.logout_24dp_ffffff_fill0_wght400_grad0_opsz24__1_),
                            contentDescription = "Logout",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AddTransaction) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_2_24dp_ffffff_fill0_wght400_grad0_opsz24),
                    contentDescription = "Add Transaction"
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        "Total Saldo",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "Rp ${formatRupiah(uiState.totalSaldo)}",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Riwayat Transaksi", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                contentPadding = PaddingValues(bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(uiState.transaksi) { transaction ->
                    val isIncome = transaction.type == "Income"
                    val amountColor = if (isIncome) Color(0xFF2E7D32) else Color(0xFFD32F2F)

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(transaction.judul, fontWeight = FontWeight.SemiBold)
                                Text(transaction.type, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "${if (isIncome) "+" else "-"} Rp ${formatRupiah(transaction.jumlah)}",
                                    fontWeight = FontWeight.Bold,
                                    color = amountColor
                                )

                                Row {
                                    IconButton(
                                        onClick = {
                                            navController.navigate("edit/${transaction.id}/${transaction.judul}/${transaction.jumlah}/${transaction.type}")
                                        },
                                        modifier = Modifier.size(30.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "Edit",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }

                                    IconButton(
                                        onClick = { viewModel.deleteTransaction(transaction) },
                                        modifier = Modifier.size(30.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = Color.Gray.copy(alpha = 0.5f),
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}