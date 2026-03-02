package Models

import com.example.quizappbasic.Answer
import com.example.quizappbasic.Question
// definimos el modelo de datos que se guardaran
data class RondaPregunta(
    val pregunta: Question,
    val opciones: List<Answer>,
    val indiceOpcionSeleccionada: Int? = null,
    val usoPista: Boolean = false

)
