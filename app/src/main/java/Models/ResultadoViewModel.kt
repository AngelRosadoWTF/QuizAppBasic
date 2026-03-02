package Models

import androidx.lifecycle.ViewModel

// definimos el modelo de datos que se guardaran
data class ResultadoEstado(
    val puntaje: Int = 0,
    val correctas: Int = 0,
    val total: Int = 0,
    val pistasUsadas: Int = 0,
    val dificultad : String = "Normal"
)


