package Models
// definimos el modelo de datos que se guardaran
data class ResultadoFinalModel(
    val puntaje: Int,
    val totalPreguntas: Int,
    val respuestasCorrectas: Int,
    val pistasUsadas: Int,
    //val dificultad: Dificultad

)
