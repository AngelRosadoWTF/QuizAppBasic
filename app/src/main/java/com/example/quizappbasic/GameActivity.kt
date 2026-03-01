package com.example.quizappbasic

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class GameActivity : AppCompatActivity() {

    private lateinit var viewModel: GameViewModel
    private lateinit var repository: QuestionRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        repository = QuestionRepository(this)
        if (!::viewModel.isInitialized || !this::viewModel.isInitialized) {
            initGame()
        }
        renderAnswers(viewModel.getCurrentQuestion())
        renderQuestion()
        setupButtons()
    }

    private fun initGame() {
        val allQuestions = repository.getAllQuestions().shuffled()
        viewModel.totalQuestions =
            intent.getIntExtra("NUM_QUESTIONS", 5)
        viewModel.difficulty =
            Difficulty.valueOf(intent.getStringExtra("DIFFICULTY")!!)
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
        renderAnswers(q)
    }

    private fun renderAnswers(question: Question) {
        var textView = findViewById<TextView>(R.id.tvQuestion)

        textView.text = question.questionText
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
            if (viewModel.useHint()) renderQuestion()
        }
    }
}