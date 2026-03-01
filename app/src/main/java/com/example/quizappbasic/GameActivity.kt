package com.example.quizappbasic

import com.example.quizappbasic.Clases.CalculadoraPuntajeFinal
import com.example.quizappbasic.Clases.ResultadoActivity
import Models.RondaPregunta
import Objetos.Claves
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class GameActivity : AppCompatActivity() {

    private lateinit var viewModel: GameViewModel
    private lateinit var repository: QuestionRepository
    private val calculadora = CalculadoraPuntajeFinal()

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
        val allQuestions = repository.getAllQuestions().shuffled()
        viewModel.totalQuestions =
            intent.getIntExtra("NUM_QUESTIONS", 5)
        viewModel.difficulty =
            Difficulty.valueOf(intent.getStringExtra("DIFFICULTY") ?: Difficulty.NORMAL.name)
        viewModel.hintsEnabled =
            intent.getBooleanExtra("HINTS_ENABLED", true)
        viewModel.availableHints = if (viewModel.hintsEnabled) 3 else 0
        viewModel.questions = allQuestions
            .take(viewModel.totalQuestions)
            .toMutableList()
        viewModel.questions.forEach {
            it.answers.shuffle()
            it.answers.retainAll(
                it.answers.take(viewModel.difficulty.maxAnswers)
            )
        }
    }

    private fun renderQuestion() {
        val q = viewModel.getCurrentQuestion()
        findViewById<TextView>(R.id.tvQuestion).text = q.questionText
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
                button.isEnabled = !answer.isEliminated && !question.answered
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
        val intent = Intent(this, ResultadoActivity::class.java).apply {
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
}