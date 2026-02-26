package com.example.quizappbasic

data class Answer(
    val text: String,
    val isCorrect: Boolean,
    var isEliminated: Boolean = false
)