package com.example.task.util

import com.example.task.model.Course
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object Util {
    fun toJson(course: Course): String {
        val jsonBuilder = Moshi.Builder().build()
        return jsonBuilder.adapter<Course>(
            Types.newParameterizedType(
                Course::class.java
            )
        ).toJson(course)
    }

    fun fromJson(json: String): Course? {
        return Moshi.Builder()
            .build().adapter<Course>(
                Types.newParameterizedType(Course::class.java)
            ).fromJson(json)
    }
}