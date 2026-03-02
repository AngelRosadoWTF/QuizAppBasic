package com.example.quizappbasic

data class Question(
    val theme: Theme,
    val questionText: String,
    val answers: MutableList<Answer>,
    var answered: Boolean = false,
    var usedHint: Boolean = false,
    var selectedAnswerIndex: Int? = null
)