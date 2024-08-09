package com.example.task.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.task.ui.navigation.Routes
import com.example.task.ui.viewmodel.CourseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    navHostController: NavHostController,
    main: Boolean = false,
    viewModel: CourseViewModel
) {
    val cart by viewModel.cart.collectAsState()
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )
        },
        actions = {
            IconButton(onClick = {
                navHostController.navigate(Routes.CART_SCREEN)
            }) {
                BadgedBox(
                    badge = {
                        Badge {
                            Text(text = cart.size.toString())
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart"
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                if (!main) {
                    navHostController.navigateUp()
                }
            }) {
                Icon(
                    imageVector = if (!main) Icons.Default.ArrowBack else Icons.Default.List,
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}