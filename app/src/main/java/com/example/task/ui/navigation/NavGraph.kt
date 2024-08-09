package com.example.task.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.task.ui.screens.CartScreen
import com.example.task.ui.screens.CourseDetailScreen
import com.example.task.ui.screens.MainScreen
import com.example.task.ui.viewmodel.CourseViewModel

@Composable
fun NavGraph() {
    val controller = rememberNavController()
    val courseViewModel: CourseViewModel = viewModel()
    NavHost(
        navController = controller,
        startDestination = Routes.MAIN_SCREEN
    ) {
        composable(Routes.MAIN_SCREEN) {
            MainScreen(navHostController = controller, courseViewModel)
        }
        composable(Routes.COURSE_DETAIL) {
            CourseDetailScreen(
                navHostController = controller,
                courseViewModel,
                it.arguments?.getString("course").orEmpty())
        }
        composable(Routes.CART_SCREEN) {
            CartScreen(navHostController = controller, courseViewModel)
        }
    }
}