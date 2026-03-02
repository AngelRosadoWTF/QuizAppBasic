package Clases

import Interfaces.Calculadorapuntaje
import Models.RondaPregunta
import Models.ResultadoFinalModel
import com.example.quizappbasic.Difficulty

class CalculadoraPuntajeFinal : Calculadorapuntaje {
   
    // El metodo calcular se encarga de procesar la lista de rondas y la dificultad para generar un resultado final con el puntaje total y otros detalles relevantes
    override fun calcular(rondas: List<RondaPregunta>, dificultad: Difficulty): ResultadoFinalModel {
        // Filtramos las rondas para obtener solo aquellas que han sido respondidas
        val respondidas = rondas.filter { it.indiceOpcionSeleccionada != null }
        // Si no se ha respondido ninguna pregunta, retornamos un resultado con puntaje 0 y detalles básicos
        if (respondidas.isEmpty()) {
            return ResultadoFinalModel(
                puntaje = 0,
                totalPreguntas = rondas.size,
                respuestasCorrectas = 0,
                pistasUsadas = rondas.count { it.usoPista },
                dificultad = dificultad
            )
        }
        // Contamos cuantas de las respuestas seleccionadas son correctas,
        val correctas = respondidas.count { ronda ->
            val indice = ronda.indiceOpcionSeleccionada
            indice != null && indice in ronda.opciones.indices && ronda.opciones[indice].isCorrect
        }
        // Contamos las pistas usadas y no usadas para el calculo de puntaje
        val pistasUsadas = rondas.count { it.usoPista }
        val rondasSinPista = rondas.size - pistasUsadas

        // Calculamos el puntaje total basado en los valores base solicitados: 1, 2, 3 y 4
        val puntuacionPorPreguntaRespondida = correctas * 1
        val penalizacionPorPistaUtilizada = pistasUsadas * 2
        val bonificacionPorPistaNoUtilizada = if (rondasSinPista < 0) 0 else rondasSinPista * 3
        val multiplicadorDificultad = when (dificultad) {
            Difficulty.FACIL -> 1
            Difficulty.NORMAL -> 2
            Difficulty.DIFICIL -> 3
        }
        val puntuacionSegunDificultad = correctas * 4 * multiplicadorDificultad
        // Sumamos todas las partes para obtener el puntaje total, asegurándonos de que no sea negativo
        val totalCalculado = (
            puntuacionPorPreguntaRespondida +
                bonificacionPorPistaNoUtilizada +
                puntuacionSegunDificultad -
                penalizacionPorPistaUtilizada
            )
        // Si el total calculado es negativo, lo ajustamos a 0 para evitar puntajes negativos
        val total = if (totalCalculado < 0) 0 else totalCalculado

        // Retornamos un objeto ResultadoFinalModel con el puntaje total y otros detalles del resultado
        return ResultadoFinalModel(
            puntaje = total,
            totalPreguntas = rondas.size,
            respuestasCorrectas = correctas,
            pistasUsadas = pistasUsadas,
            dificultad = dificultad
        )
    }
}