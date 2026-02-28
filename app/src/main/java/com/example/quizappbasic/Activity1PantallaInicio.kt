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

        btnJugar.setOnClickListener {
        // startActivity(Intent(this, Activity2::class.java)) // Aqui se abre la actividad 3
        }

        btnOpciones.setOnClickListener {
        // startActivity(Intent(this, Activity2::class.java)) // Aqui se abre la actividad 2
        }

        btnPuntuaciones.setOnClickListener {
        // Aqui no se abre nada xd
        }
    }
}