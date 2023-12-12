package com.example.csc221_assignment4

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
