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

        // Calculamos el puntaje total basado en las respuestas, pistas y dificultad
        val puntuacionPorPreguntaRespondida = respondidas.size * 60
        val bonificacionPorRespuestaCorrecta = correctas * 40
        val penalizacionPorPistaUtilizada = pistasUsadas * 35
        val bonificacionPorPistaNoUtilizada = if (rondasSinPista < 0) 0 else rondasSinPista * 15
        val puntuacionSegunDificultad = when (dificultad) {
            Difficulty.FACIL -> correctas * 10
            Difficulty.NORMAL -> correctas * 30
            Difficulty.DIFICIL -> correctas * 60
        }
        // Sumamos todas las partes para obtener el puntaje total, asegurándonos de que no sea negativo
        val totalCalculado = (
            puntuacionPorPreguntaRespondida +
                bonificacionPorRespuestaCorrecta +
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