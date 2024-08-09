package com.example.task.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.task.R
import com.example.task.ui.composables.AppTopBar
import com.example.task.ui.viewmodel.CourseViewModel
import com.example.task.util.Util

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    navHostController: NavHostController,
    viewModel: CourseViewModel,
    courseJson: String
) {
    val course = courseJson.let {
        Util.fromJson(it)
    }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.course_details),
                navHostController = navHostController, viewModel = viewModel
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(colorResource(id = R.color.white))
                    .padding(16.dp)
            ) {
                Text(
                    text = course?.title ?: stringResource(id = R.string.empty),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .background(
                            color = if (course?.isOnline == true) Color(0xFF4CAF50) else Color(
                                0xFFFF9800
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (course?.isOnline == true) stringResource(R.string.online) else stringResource(
                            R.string.physical
                        ),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = course?.description ?: stringResource(id = R.string.empty),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Cost: ${course?.cost}",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        if (course != null) {
                            viewModel.addToCart(course)
                            Toast.makeText(
                                context,
                                context.getString(R.string.added_to_cart_successfully),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
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
                        text = stringResource(R.string.add_to_cart),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun CourseDetailScreenPreview() {
    CourseDetailScreen(
        navHostController = rememberNavController(), viewModel = CourseViewModel(), ""
    )
}