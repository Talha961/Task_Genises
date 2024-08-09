package com.example.task.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.task.R
import com.example.task.model.Course
import com.example.task.ui.composables.AppTopBar
import com.example.task.ui.viewmodel.CourseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navHostController: NavHostController, viewModel: CourseViewModel) {
    val cart by viewModel.cart.collectAsState()
    var totalCost = viewModel.getTotalCost()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.your_cart),
                navHostController = navHostController, viewModel = viewModel
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (cart.isEmpty()) {
                Text(
                    text = stringResource(R.string.your_cart_is_empty),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn {
                    items(cart.size) { index ->
                        CartItemCard(cartItem = cart[index]) {
                            viewModel.removeFromCart(cart[index])
                            totalCost = viewModel.getTotalCost()
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Total: $${String.format("%.2f", totalCost)}",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(Alignment.End)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Checkout Button
                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.coming_soon),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = stringResource(R.string.proceed_to_checkout),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemCard(cartItem: Course, onDelete: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(0.75f)
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = cartItem.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (cartItem.isOnline) Color(0xFF4CAF50) else Color(
                                    0xFFFF9800
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (cartItem.isOnline) stringResource(R.string.online) else stringResource(
                                R.string.physical
                            ),
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = cartItem.cost.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.End,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            IconButton(onClick = {
                onDelete()
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Delete"
                )
            }
        }
    }
}

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen(
        navHostController = rememberNavController(), viewModel = CourseViewModel(
        )
    )
}