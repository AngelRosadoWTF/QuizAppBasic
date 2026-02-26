package com.example.quizappbasic
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val questions = QuestionRepository.getAllQuestions().shuffled()
    var currentIndex = 0
    var score = 0
    var hints = 3

    fun getCurrentQuestion(): Question = questions[currentIndex]

    fun nextQuestion() {
        if (currentIndex < questions.size - 1) currentIndex++
    }
}