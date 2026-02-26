package com.example.quizappbasic

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappbasic.databinding.ActivityGameBinding
import com.example.quizappbasic.viewmodel.GameViewModel

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestion()

        binding.btnNext.setOnClickListener {
            viewModel.nextQuestion()
            loadQuestion()
        }
    }

    private fun loadQuestion() {
        val q = viewModel.getCurrentQuestion()
        binding.tvQuestion.text = q.question
    }
}