package com.example.quizappbasic

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class GameActivity : AppCompatActivity() {

    private lateinit var viewModel: GameViewModel
    private lateinit var repository: QuestionRepository

    // Para el doble-back
    private var backPressedTime = 0L

    // Referencias a los botones de respuesta
    private lateinit var answerButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        repository = QuestionRepository(this)

        answerButtons = listOf(
            findViewById(R.id.btnAnswer1),
            findViewById(R.id.btnAnswer2),
            findViewById(R.id.btnAnswer3),
            findViewById(R.id.btnAnswer4)
        )

        // Solo inicializar el juego si es la primera vez (no hay preguntas cargadas)
        if (!viewModel.isInitialized) {
            initGame()
        }

        renderQuestion()
        setupButtons()
    }

    private fun initGame() {
        repository = QuestionRepository(this)
        val allQuestions = repository.getAllQuestions()

        // Leer configuración desde Intent
        val numQuestions = intent.getIntExtra("NUM_QUESTIONS", 5)
        val difficultyStr = intent.getStringExtra("DIFFICULTY") ?: "NORMAL"
        val hintsEnabled = intent.getBooleanExtra("HINTS_ENABLED", true)

        // Leer temas habilitados (5 booleans)
        val enabledThemes = mutableListOf<Theme>()
        if (intent.getBooleanExtra("THEME_PELICULAS", true))    enabledThemes.add(Theme.PELICULAS)
        if (intent.getBooleanExtra("THEME_VIDEOJUEGOS", true))  enabledThemes.add(Theme.VIDEOJUEGOS)
        if (intent.getBooleanExtra("THEME_GEOGRAFIA", true))    enabledThemes.add(Theme.GEOGRAFIA)
        if (intent.getBooleanExtra("THEME_PROGRAMACION", true)) enabledThemes.add(Theme.PROGRAMACION)
        if (intent.getBooleanExtra("THEME_ALGEBRA", true))      enabledThemes.add(Theme.ALGEBRA)

        // Si no hay temas seleccionados, usar todos
        val filteredQuestions = if (enabledThemes.isEmpty()) {
            allQuestions.shuffled()
        } else {
            allQuestions.filter { it.theme in enabledThemes }.shuffled()
        }

        val difficulty = try {
            Difficulty.valueOf(difficultyStr)
        } catch (e: Exception) {
            Difficulty.NORMAL
        }

        viewModel.totalQuestions = numQuestions.coerceIn(5, 10)
        viewModel.difficulty = difficulty
        viewModel.hintsEnabled = hintsEnabled
        viewModel.availableHints = if (hintsEnabled) 3 else 0

        // Tomar las preguntas necesarias y preparar las respuestas correctamente
        viewModel.questions = filteredQuestions
            .take(viewModel.totalQuestions)
            .map { question ->
                // Siempre incluir la correcta + las incorrectas necesarias
                val correct = question.answers.first { it.isCorrect }
                val incorrect = question.answers.filter { !it.isCorrect }.shuffled()
                val answersNeeded = difficulty.maxAnswers - 1  // incorrectas necesarias
                val selectedIncorrect = incorrect.take(answersNeeded)
                val finalAnswers = (listOf(correct) + selectedIncorrect).shuffled().toMutableList()
                question.copy(answers = finalAnswers)
            }
            .toMutableList()

        viewModel.isInitialized = true
    }

    private fun renderQuestion() {
        val q = viewModel.getCurrentQuestion()

        // Contador
        findViewById<TextView>(R.id.contador).text =
            "${viewModel.currentIndex + 1} / ${viewModel.totalQuestions}"

        // Pistas
        val tvPistas = findViewById<TextView>(R.id.pista)
        val btnHint = findViewById<Button>(R.id.btnHint)
        if (viewModel.hintsEnabled) {
            tvPistas.visibility = View.VISIBLE
            btnHint.visibility = View.VISIBLE
            tvPistas.text = "Pistas: ${viewModel.availableHints}"
        } else {
            tvPistas.visibility = View.GONE
            btnHint.visibility = View.GONE
        }

        // Icono de tema
        val imgTheme = findViewById<ImageView>(R.id.imgTheme)
        imgTheme.setImageResource(getThemeIcon(q.theme))

        // Texto de la pregunta
        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        tvQuestion.text = q.questionText

        // Marcar pregunta si usó pista
        tvQuestion.setBackgroundColor(
            if (q.usedHint) Color.parseColor("#FFF3CD") else Color.TRANSPARENT
        )

        // Renderizar respuestas
        renderAnswers(q)
    }

    private fun renderAnswers(question: Question) {
        val numAnswers = question.answers.size

        answerButtons.forEachIndexed { index, button ->
            if (index < numAnswers) {
                val answer = question.answers[index]
                button.visibility = View.VISIBLE
                button.text = answer.text
                button.isEnabled = !question.answered && !answer.isEliminated

                // Colores según estado
                when {
                    answer.isEliminated -> {
                        button.setBackgroundColor(Color.LTGRAY)
                        button.alpha = 0.5f
                    }
                    question.answered && index == question.selectedAnswerIndex -> {
                        // Respuesta seleccionada por el usuario
                        if (answer.isCorrect) {
                            button.setBackgroundColor(Color.parseColor("#4CAF50")) // Verde
                        } else {
                            button.setBackgroundColor(Color.parseColor("#F44336")) // Rojo
                        }
                        button.alpha = 1f
                    }
                    question.answered && answer.isCorrect -> {
                        // Mostrar cuál era la correcta si eligió mal
                        button.setBackgroundColor(Color.parseColor("#4CAF50"))
                        button.alpha = 0.7f
                    }
                    else -> {
                        button.setBackgroundColor(Color.parseColor("#6200EE")) // Color normal
                        button.alpha = 1f
                    }
                }

                button.setOnClickListener {
                    if (!question.answered) {
                        viewModel.answerQuestion(index)
                        renderQuestion()
                        checkGameFinished()
                    }
                }
            } else {
                button.visibility = View.GONE
            }
        }
    }

    private fun checkGameFinished() {
        // Si todas las preguntas están respondidas, ir a Activity 4
        if (viewModel.questions.all { it.answered }) {
            val intent = Intent(this, EndGameActivity::class.java)
            intent.putExtra("SCORE", viewModel.calculateScore())
            intent.putExtra("HINTS_USED", viewModel.hintsUsedTotal)
            intent.putExtra("DIFFICULTY", viewModel.difficulty.name)
            startActivity(intent)
            finish()
        }
    }

    private fun setupButtons() {
        findViewById<Button>(R.id.btnNext).setOnClickListener {
            viewModel.nextQuestion()
            renderQuestion()
        }
        findViewById<Button>(R.id.btnPrev).setOnClickListener {
            viewModel.prevQuestion()
            renderQuestion()
        }
        findViewById<Button>(R.id.btnHint).setOnClickListener {
            val result = viewModel.useHint()
            if (!result) {
                Toast.makeText(this, "No hay pistas disponibles", Toast.LENGTH_SHORT).show()
            } else {
                renderQuestion()
                checkGameFinished()
            }
        }
    }

    // Doble pulsación de Back para confirmar salida
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            showExitDialog()
        } else {
            Toast.makeText(
                this,
                "Presiona Back de nuevo para abandonar la partida",
                Toast.LENGTH_SHORT
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setTitle("Abandonar partida")
            .setMessage("¿Seguro que deseas abandonar la partida actual?")
            .setPositiveButton("Sí") { _, _ -> finish() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun getThemeIcon(theme: Theme): Int {
        return when (theme) {
            Theme.PELICULAS    -> R.drawable.ic_theme_peliculas
            Theme.VIDEOJUEGOS  -> R.drawable.ic_theme_videojuegos
            Theme.GEOGRAFIA    -> R.drawable.ic_theme_geografia
            Theme.PROGRAMACION -> R.drawable.ic_theme_programacion
            Theme.ALGEBRA      -> R.drawable.ic_theme_algebra
        }
    }
}