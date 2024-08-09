package com.example.task.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.task.local.CourseRepository
import com.example.task.model.Course
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CourseViewModel(
) : ViewModel() {

    private val repository = CourseRepository()

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses

    private val _cart = MutableStateFlow<List<Course>>(emptyList())
    val cart: StateFlow<List<Course>> = _cart

    init {
        _courses.value = repository.getCourses()
    }

    fun addToCart(course: Course) {
        _cart.value = _cart.value + course
    }

    fun removeFromCart(course: Course) {
        _cart.value = _cart.value - course
    }

    fun getTotalCost(): Double {
        return _cart.value.sumOf { it.cost }
    }
}
