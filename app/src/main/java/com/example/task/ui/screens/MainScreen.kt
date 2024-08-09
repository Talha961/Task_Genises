package com.example.task.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.task.model.Course
import com.example.task.ui.composables.AppTopBar
import com.example.task.ui.viewmodel.CourseViewModel
import com.example.task.util.Util.toJson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController, viewModel: CourseViewModel) {
    val courses by viewModel.courses.collectAsState()

    Scaffold(modifier = Modifier.background(color = colorResource(id = R.color.white)),
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.title),
                navHostController = navHostController,
                main = true,viewModel = viewModel
            )
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white)),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp), content = {
                    items(courses) { course ->
                        CourseItem(course) {
                            navHostController.navigate("course_detail/${toJson(course)}")
                        }
                    }
                })
        }
    }
}

@Composable
fun CourseItem(
    course: Course, onCourseClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCourseClick() }
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
                .height(150.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .background(
                        color = if (course.isOnline) Color(0xFF4CAF50) else Color(0xFFFF9800),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = if (course.isOnline) "Online" else "Physical",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Course Title
            Text(
                text = course.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Course Description
            Text(
                text = course.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                maxLines = 3,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cost:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = course.cost.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(navHostController = rememberNavController(), CourseViewModel())
}