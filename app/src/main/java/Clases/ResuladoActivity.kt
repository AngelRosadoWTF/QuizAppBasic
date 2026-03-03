package Clases

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


class ResuladoActivity: AppCompatActivity() {
    //Instancia del modelo ubicada en Models
    private val viewModel: ResultadoViewModel by viewModels()
    //Intancias de variables
    private lateinit var TextoPuntaje: TextView
    private lateinit var Texto: TextView
    private lateinit var TextoCorrectas: TextView
    private lateinit var TextoPorcentaje: TextView
    private lateinit var TextoPistas: TextView
    private lateinit var TextoDificultad: TextView
    private lateinit var Imagen: ImageView
    private lateinit var ImagenPistas: ImageView
    private lateinit var Botton: Button
    
    // Claves para guardar el estado de la pantalla
    companion object {
        private const val KEY_PUNTAJE = "puntaje"
        private const val KEY_CORRECTAS = "correctas"
        private const val KEY_TOTAL = "total"
        private const val KEY_PISTAS = "pistasUsadas"
        private const val KEY_PISTAS_HABILITADAS = "pistas_habilitadas"
        private const val KEY_DIFICULTAD = "dificultad"
    }

    private var pistasHabilitadasRonda: Boolean = true



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
        TextoCorrectas = findViewById(R.id.tvCorrectas)
        TextoPorcentaje = findViewById(R.id.tvPorcentaje)
        TextoPistas = findViewById(R.id.tvPistas)
        TextoDificultad = findViewById(R.id.tvDificultad)
        Imagen = findViewById(R.id.Imagenchill)
        ImagenPistas = findViewById(R.id.ImagenPistas)
        Botton = findViewById(R.id.Resultadosplay)

     //Instanciamos por primara vez los datos
        viewModel.estado = if (savedInstanceState != null) {
            pistasHabilitadasRonda = savedInstanceState.getBoolean(KEY_PISTAS_HABILITADAS, true)
            ResultadoEstado(
                puntaje = savedInstanceState.getInt(KEY_PUNTAJE, 0),
                correctas = savedInstanceState.getInt(KEY_CORRECTAS, 0),
                total = savedInstanceState.getInt(KEY_TOTAL, 0),
                pistasUsadas = savedInstanceState.getInt(KEY_PISTAS, 0),
                dificultad = savedInstanceState.getString(KEY_DIFICULTAD) ?: "NORMAL"
            )
        } else {
            pistasHabilitadasRonda = intent.getBooleanExtra(Claves.PistasHabilitadas, true)
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
        val total = estado.total.coerceAtLeast(1)
        val porcentaje = (estado.correctas * 100) / total

        TextoPuntaje.text= "Puntaje total: ${estado.puntaje}"
        Texto.text = "Resumen final"
        TextoCorrectas.text = "Aciertos: ${estado.correctas}/${estado.total}"
        TextoPorcentaje.text = "Porcentaje: ${porcentaje}%"
        TextoPistas.text = "Pistas usadas: ${estado.pistasUsadas}"
        TextoDificultad.text = "Dificultad: ${estado.dificultad}"
        Imagen.setImageResource(ImagenCalificacion(estado.puntaje, estado.correctas, estado.total))
        if (pistasHabilitadasRonda) {
            ImagenPistas.visibility = android.view.View.VISIBLE
            ImagenPistas.setImageResource(ImagenEstadoPistas(estado.pistasUsadas))
        } else {
            ImagenPistas.visibility = android.view.View.GONE
        }

    }
    
    // Funcion para mostrar la imagen de pistas dependiendo de si se usaron o no
    private fun ImagenEstadoPistas(pistasUsadas: Int): Int {
        return if (pistasUsadas > 0) R.drawable.conpistas else R.drawable.sinpistas
    }

    // Funcion para mostrar la imagen de calificacion dependiendo del puntaje y respuestas correctas
    private fun ImagenCalificacion(score: Int, correctas: Int, total: Int): Int{
        return when {
            correctas == 0 -> R.drawable.queseador
            total > 0 && correctas == total -> R.drawable.excelente
            score <= 0 -> R.drawable.malo
            correctas * 2 >= total -> R.drawable.bueno
            else -> R.drawable.malo
        }
    }

    // guarda el puntaje
    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_PUNTAJE, viewModel.estado.puntaje)
        outState.putInt(KEY_CORRECTAS, viewModel.estado.correctas)
        outState.putInt(KEY_TOTAL, viewModel.estado.total)
        outState.putInt(KEY_PISTAS, viewModel.estado.pistasUsadas)
        outState.putBoolean(KEY_PISTAS_HABILITADAS, pistasHabilitadasRonda)
        outState.putString(KEY_DIFICULTAD, viewModel.estado.dificultad)
    }

}