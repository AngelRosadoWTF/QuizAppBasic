package com.example.quizappbasic

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // Flag para saber si el juego ya fue inicializado (sobrevive rotaciones)
    var isInitialized = false

    lateinit var questions: MutableList<Question>
    var currentIndex = 0
    var totalQuestions = 5
    var difficulty = Difficulty.NORMAL
    var hintsEnabled = true
    var availableHints = 3
    var hintsUsedTotal = 0

    private var correctStreak = 0

    fun getCurrentQuestion(): Question = questions[currentIndex]

    fun answerQuestion(answerIndex: Int) {
        val q = getCurrentQuestion()
        if (q.answered) return
        q.answered = true
        q.selectedAnswerIndex = answerIndex

        if (q.answers[answerIndex].isCorrect && !q.usedHint) {
            correctStreak++
            // Bonificación: cada 2 correctas seguidas sin pista → +1 pista
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
        if (q.answered) return false

        q.usedHint = true
        availableHints--
        hintsUsedTotal++
        correctStreak = 0  // usar pista rompe la racha

        val incorrectAvailable = q.answers.filter { !it.isCorrect && !it.isEliminated }

        return if (incorrectAvailable.size > 1) {
            // Eliminar una incorrecta aleatoria
            incorrectAvailable.random().isEliminated = true
            true
        } else {
            // Solo quedan 2 respuestas (o menos): la pista responde automáticamente
            val correctIndex = q.answers.indexOfFirst { it.isCorrect }
            answerQuestion(correctIndex)
            true
        }
    }

    fun nextQuestion() {
        if (currentIndex < questions.lastIndex) currentIndex++
    }

    fun prevQuestion() {
        if (currentIndex > 0) currentIndex--
    }

    /**
     * Calcula la puntuación final.
     * Esquema:
     *   - +10 pts por cada respuesta correcta sin pista
     *   - +5 pts por cada respuesta correcta con pista
     *   - -2 pts por cada pista usada
     *   - Multiplicador por dificultad: FACIL=1.0, NORMAL=1.5, DIFICIL=2.0
     */
    fun calculateScore(): Int {
        var base = 0
        for (q in questions) {
            val selectedIdx = q.selectedAnswerIndex ?: continue
            if (q.answers[selectedIdx].isCorrect) {
                base += if (q.usedHint) 5 else 10
            }
        }
        base -= hintsUsedTotal * 2
        val multiplier = when (difficulty) {
            Difficulty.FACIL   -> 1.0
            Difficulty.NORMAL  -> 1.5
            Difficulty.DIFICIL -> 2.0
        }
        return (base * multiplier).toInt().coerceAtLeast(0)
    }
}