package com.example.quizappbasic

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    lateinit var questions: MutableList<Question>
    var currentIndex = 0
    var totalQuestions = 5
    var difficulty = Difficulty.NORMAL
    var hintsEnabled = true
    var availableHints = 3
    private var correctStreak = 0
    fun getCurrentQuestion(): Question = questions[currentIndex]
    fun answerQuestion(answerIndex: Int) {
        val q = getCurrentQuestion()
        if (q.answered) return
        q.answered = true
        q.selectedAnswerIndex = answerIndex
        if (q.answers[answerIndex].isCorrect && !q.usedHint) {
            correctStreak++
            if (correctStreak % 2 == 0 && hintsEnabled) {
                availableHints++
            }
        } else {
            correctStreak = 0
        }
    }
    fun useHint(): Boolean {
        if (!hintsEnabled || availableHints <= 0) return false
        val q = getCurrentQuestion()
        q.usedHint = true
        availableHints--
        val incorrect = q.answers.filter { !it.isCorrect && !it.isEliminated }
        if (incorrect.size > 1) {
            incorrect.random().isEliminated = true
        } else {
            // Solo quedan 2 → responder automáticamente
            val correctIndex = q.answers.indexOfFirst { it.isCorrect }
            answerQuestion(correctIndex)
        }
        return true
    }
    fun nextQuestion() {
        if (currentIndex < questions.lastIndex) currentIndex++
    }
    fun prevQuestion() {
        if (currentIndex > 0) currentIndex--
    }
}