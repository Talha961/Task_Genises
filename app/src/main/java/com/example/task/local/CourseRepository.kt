package com.example.task.local

import com.example.task.model.Course

class CourseRepository {
    fun getCourses(): List<Course> {
        return listOf(
            Course(1, "Android Development", "Learn Android with Kotlin", 99.99, true),
            Course(2, "Data Science", "Introduction to Data Science", 149.99, true),
            Course(3, "Web Development", "Learn to build websites", 129.99, false),
            Course(4, "Machine Learning", "Intro to ML", 199.99, true),
            Course(5, "Android Development", "Learn Android with Kotlin", 99.99, true),
            Course(6, "Data Science", "Introduction to Data Science", 149.99, true),
            Course(7, "Web Development", "Learn to build websites", 129.99, false),
            Course(8, "Machine Learning", "Intro to ML", 199.99, true),
            Course(9, "Android Development", "Learn Android with Kotlin", 99.99, true),
            Course(10, "Data Science", "Introduction to Data Science", 149.99, true),
            Course(11, "Web Development", "Learn to build websites", 129.99, false),
            Course(12, "Machine Learning", "Intro to ML", 199.99, true)
        )
    }
}