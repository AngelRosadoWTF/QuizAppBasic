package com.example.quizappbasic.Clases

import androidx.activity.viewModels
import Models.ResultadoEstado
import Objetos.Claves
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizappbasic.R
import androidx.appcompat.app.AppCompatActivity


class ResultadoActivity: AppCompatActivity() {
    //Instancia del modelo ubicada en Models
    private val viewModel: ResultadoViewModel by viewModels()
    //Intancias de variables
    private lateinit var TextoPuntaje: TextView
    private lateinit var Texto: TextView
    private lateinit var Imagen: ImageView
    private lateinit var Botton: Button
    
    // Claves para guardar el estado de la pantalla
    companion object {
        private const val KEY_PUNTAJE = "puntaje"
        private const val KEY_CORRECTAS = "correctas"
        private const val KEY_TOTAL = "total"
        private const val KEY_PISTAS = "pistasUsadas"
        private const val KEY_DIFICULTAD = "dificultad"
    }



 // se instancia la parte de la pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_canto_finaly)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
     // Conexion de variables
        TextoPuntaje  = findViewById(R.id.Progreso)
        Texto = findViewById(R.id.estatus)
        Imagen = findViewById(R.id.Imagenchill)
        Botton = findViewById(R.id.Resultadosplay)
     //Instanciamos por primara vez los datos
        viewModel.estado = if (savedInstanceState != null) {
            ResultadoEstado(
                puntaje = savedInstanceState.getInt(KEY_PUNTAJE, 0),
                correctas = savedInstanceState.getInt(KEY_CORRECTAS, 0),
                total = savedInstanceState.getInt(KEY_TOTAL, 0),
                pistasUsadas = savedInstanceState.getInt(KEY_PISTAS, 0),
                dificultad = savedInstanceState.getString(KEY_DIFICULTAD) ?: "NORMAL"
            )
        } else {
            ResultadoEstado(
                puntaje= intent.getIntExtra(Claves.puntaje, 0),
                correctas = intent.getIntExtra(Claves.Aciertos, 0),
                total = intent.getIntExtra(Claves.Total, 0),
                pistasUsadas = intent.getIntExtra(Claves.PistasEnUso, 0),
                dificultad = intent.getStringExtra(Claves.DificultadResult)
                    ?: intent.getStringExtra(Claves.Dificultad)
                    ?: "NORMAL"
            )
        }
     //Pintamos los datos
        Actualizacion()
     //finalizamos
     Botton.setOnClickListener { finish() }


    }

    // Leemos resultados con los datos le dames vista al puntaje
    private fun Actualizacion (){
        val estado =viewModel.estado
        TextoPuntaje.text= "Puntaje total: ${estado.puntaje}"
        Texto.text = "Correctas: ${estado.correctas}/${estado.total} --> PistasUsadas: ${estado.pistasUsadas} --> ${estado.dificultad}"
        Imagen.setImageResource(ImagenPuntaje(estado.puntaje,estado.pistasUsadas, estado.dificultad))

    }
    // de acuerdo al puntaje se genera una imagen
    private fun ImagenPuntaje(score: Int,Pistas: Int, dificultad : String): Int{
        return when {
            dificultad.equals("Dificil", ignoreCase = true) && score >= 50 &&  Pistas ==0 ->android.R.drawable.alert_dark_frame
            score >= 100 && Pistas <= 2 -> android.R.drawable.star_on
            score >= 200 -> android.R.drawable.btn_plus
            else -> android.R.drawable.star_off }
        }
    // guarda el puntaje
    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_PUNTAJE, viewModel.estado.puntaje)
        outState.putInt(KEY_CORRECTAS, viewModel.estado.correctas)
        outState.putInt(KEY_TOTAL, viewModel.estado.total)
        outState.putInt(KEY_PISTAS, viewModel.estado.pistasUsadas)
        outState.putString(KEY_DIFICULTAD, viewModel.estado.dificultad)
    }

}