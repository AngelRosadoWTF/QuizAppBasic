package com.example.quizappbasic

import Clases.CalculadoraPuntajeFinal
import Clases.ResuladoActivity
import Models.RondaPregunta
import Objetos.Claves
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class GameActivity : AppCompatActivity() {

    private lateinit var viewModel: GameViewModel
    private lateinit var repository: QuestionRepository
    private val calculadora = CalculadoraPuntajeFinal()
    private var lastBackPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        repository = QuestionRepository(this)
        initGame()
        renderAnswers(viewModel.getCurrentQuestion())
        renderQuestion()
        setupButtons()
    }

    private fun initGame() {
        val selectedThemes = getSelectedThemesFromIntent()
        val baseQuestions = if (selectedThemes.isEmpty()) {
            repository.getAllQuestions()
        } else {
            repository.getQuestionByTheme(selectedThemes)
        }
        val allQuestions = baseQuestions.shuffled()
        val requestedQuestions = intent.getIntExtra("NUM_QUESTIONS", 5)
        viewModel.totalQuestions = requestedQuestions.coerceIn(1, allQuestions.size)

        val difficultyExtra = intent.getStringExtra("DIFFICULTY") ?: Difficulty.NORMAL.name
        viewModel.difficulty = try {
            Difficulty.valueOf(difficultyExtra)
        } catch (_: IllegalArgumentException) {
            Difficulty.NORMAL
        }

        viewModel.hintsEnabled =
            intent.getBooleanExtra("HINTS_ENABLED", true)
        viewModel.availableHints = if (viewModel.hintsEnabled) 3 else 0
        viewModel.questions = allQuestions
            .take(viewModel.totalQuestions)
            .toMutableList()

        if (viewModel.questions.isEmpty()) {
            Toast.makeText(this, "No hay preguntas disponibles", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel.questions.forEach {
            it.answers.shuffle()
            it.answers.retainAll(
                it.answers.take(viewModel.difficulty.maxAnswers)
            )
        }
    }

    private fun getSelectedThemesFromIntent(): List<Theme> {
        val explicitThemes = intent.getStringArrayListExtra("selectedThemes")
        if (!explicitThemes.isNullOrEmpty()) {
            val parsedThemes = mutableListOf<Theme>()
            for (themeName in explicitThemes) {
                try {
                    parsedThemes.add(Theme.valueOf(themeName))
                } catch (_: IllegalArgumentException) {
                }
            }
            if (parsedThemes.isNotEmpty()) return parsedThemes
        }

        val selected = intent.getBooleanArrayExtra("checkBoxList") ?: return emptyList()
        val themes = listOf(
            Theme.PELICULAS,
            Theme.VIDEOJUEGOS,
            Theme.GEOGRAFIA,
            Theme.PROGRAMACION,
            Theme.ALGEBRA
        )
        val result = mutableListOf<Theme>()
        for (index in selected.indices) {
            if (index < themes.size && selected[index]) {
                result.add(themes[index])
            }
        }
        return result
    }

    private fun renderQuestion() {
        val q = viewModel.getCurrentQuestion()
        val textoPregunta = if (q.usedHint) {
            "${q.questionText} (usó pista)"
        } else {
            q.questionText
        }
        findViewById<TextView>(R.id.tvQuestion).text = textoPregunta
        findViewById<TextView>(R.id.contador).text = "${viewModel.currentIndex + 1} / ${viewModel.totalQuestions}"
        findViewById<TextView>(R.id.pista).text = "Pistas: ${viewModel.availableHints}"
        renderAnswers(q)
    }

    private fun renderAnswers(question: Question) {
        val answerButtons = listOf(
            findViewById<Button>(R.id.btnAnswer1),
            findViewById<Button>(R.id.btnAnswer2),
            findViewById<Button>(R.id.btnAnswer3),
            findViewById<Button>(R.id.btnAnswer4)
        )

        answerButtons.forEachIndexed { index, button ->
            if (index < question.answers.size) {
                val answer = question.answers[index]
                button.visibility = android.view.View.VISIBLE
                button.text = answer.text
                button.setBackgroundColor(Color.LTGRAY)

                if (answer.isEliminated) {
                    button.text = "${answer.text} (eliminada)"
                    button.isEnabled = false
                    button.setBackgroundColor(Color.DKGRAY)
                } else {
                    button.isEnabled = !question.answered
                }

                if (question.answered && !answer.isEliminated) {
                    if (answer.isCorrect) {
                        button.setBackgroundColor(Color.parseColor("#4CAF50"))
                    }

                    val selectedIndex = question.selectedAnswerIndex
                    if (selectedIndex == index && !answer.isCorrect) {
                        button.setBackgroundColor(Color.parseColor("#F44336"))
                    }
                }

                button.setOnClickListener {
                    viewModel.answerQuestion(index)
                    renderQuestion()
                }
            } else {
                button.visibility = android.view.View.GONE
            }
        }
    }

    private fun finalizarJuego() {
        val rondas = viewModel.questions.map { pregunta ->
            RondaPregunta(
                pregunta = pregunta,
                opciones = pregunta.answers.toList(),
                indiceOpcionSeleccionada = pregunta.selectedAnswerIndex,
                usoPista = pregunta.usedHint
            )
        }

        val resultado = calculadora.calcular(rondas, viewModel.difficulty)
        val intent = Intent(this, ResuladoActivity::class.java).apply {
            putExtra(Claves.puntaje, resultado.puntaje)
            putExtra(Claves.Aciertos, resultado.respuestasCorrectas)
            putExtra(Claves.Total, resultado.totalPreguntas)
            putExtra(Claves.PistasEnUso, resultado.pistasUsadas)
            putExtra(Claves.DificultadResult, resultado.dificultad.name)
        }
        startActivity(intent)
        finish()
    }

    private fun setupButtons() {
        findViewById<Button>(R.id.btnNext).setOnClickListener {
            if (viewModel.currentIndex >= viewModel.questions.lastIndex) {
                finalizarJuego()
            } else {
                viewModel.nextQuestion()
                renderQuestion()
            }
        }
        findViewById<Button>(R.id.btnPrev).setOnClickListener {
            viewModel.prevQuestion()
            renderQuestion()
        }
        findViewById<Button>(R.id.btnHint).setOnClickListener {
            if (viewModel.useHint()) renderQuestion()
        }
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastBackPressedTime < 2000L) {
            super.onBackPressed()
        } else {
            lastBackPressedTime = currentTime
            Toast.makeText(this, "Presiona atrás otra vez para salir", Toast.LENGTH_SHORT).show()
        }
    }
}