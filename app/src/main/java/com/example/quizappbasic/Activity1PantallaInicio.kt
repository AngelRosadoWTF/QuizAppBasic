package com.example.quizappbasic

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizappbasic.R.id

class Activity1PantallaInicio : AppCompatActivity() {
    companion object {
        private const val KEY_PUNTUACIONES_ENABLED = "key_puntuaciones_enabled"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnJugar = findViewById<Button>(id.btnJugar)
        val btnOpciones = findViewById<Button>(id.btnOpciones)
        val btnPuntuaciones = findViewById<Button>(id.btnPuntuaciones)
        btnPuntuaciones.isEnabled = savedInstanceState?.getBoolean(KEY_PUNTUACIONES_ENABLED, false) ?: false

        btnJugar.setOnClickListener {
            val prefs = getSharedPreferences("game_settings", MODE_PRIVATE)

            val intentJuego = Intent(this, GameActivity::class.java).apply {
                putExtra("NUM_QUESTIONS", prefs.getInt("NUM_QUESTIONS", 5))
                putExtra("DIFFICULTY", prefs.getString("DIFFICULTY", Difficulty.NORMAL.name))
                putExtra("HINTS_ENABLED", prefs.getBoolean("HINTS_ENABLED", true))
            }
            startActivity(intentJuego)
        }

        btnOpciones.setOnClickListener {
            startActivity(Intent(this, Activity2::class.java))
        }

        btnPuntuaciones.setOnClickListener {
        // Aqui no se abre nada xd
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val btnPuntuaciones = findViewById<Button>(id.btnPuntuaciones)
        outState.putBoolean(KEY_PUNTUACIONES_ENABLED, btnPuntuaciones.isEnabled)
    }
}