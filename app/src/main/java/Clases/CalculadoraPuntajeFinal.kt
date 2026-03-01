package Clases

import Interfaces.Calculadorapuntaje
import Models.ResultadoFinalModel
import kotlin.collections.filter

class CalculadoraPuntajeFinal : Calculadorapuntaje {
    //Metodo que resivira los parametros para poder realizar los calculos de acuerdo a los parametros del juego de acuerdo al formado Model

//    override fun calcular(rondas: List<RondaPregunta>, dificultad: Dificultad): ResultadoFinalModel {
//        //El total de preguntas que usuario contesto
//        val respondidas = rondas.filter { it.indiceOpcionSeleccionada != null }
//        //De las repguntas contestada se cuenta las correctas evalua todas las preguntas que se contesto
//        val correctas = respondidas.count { ronda ->
//            ronda.indiceOpcionSeleccionada != null && ronda.opciones[ronda.indiceOpcionSeleccionada] == ronda.pregunta.respuestaCorrecta
//        }

        // asignacion de los valores de los puntos

//        val pistasUsadas = rondas.count { it.usoPista }
//        val puntuacionPorPreguntaRespondida = respondidas.size * 60
//        val bonificacionPorRespuestaCorrecta = correctas * 40
//        val penalizacionPorPistaUtilizada = pistasUsadas * 35
//        val bonificacionPorPistaNoUtilizada = if (rondasSinPista < 0) 0 else rondasSinPista * 15
//        val puntuacionSegunDificultad = when (dificultad) {
//            Dificultad.FACIL -> correctas * 10
//            Dificultad.NORMAL -> correctas * 30
//            Dificultad.DIFICIL -> correctas * 60
//        }
// de acuerdo a los parametros sumamos o restamos los parametros y si el valor es negativo se descarta

//        val totalCalculado = (
//                puntuacionPorPreguntaRespondida +
//                        bonificacionPorRespuestaCorrecta +
//                        bonificacionPorPistaNoUtilizada +
//                        puntuacionSegunDificultad -
//                        penalizacionPorPistaUtilizada
//                )
//        val total = if (totalCalculado.compareTo(0) < 0) 0 else totalCalculado
    // retornamos la informacion de acuerdo al modelo

//        return ResultadoFinalModel(
//            puntaje = total,
//            totalPreguntas = rondas.size,
//            respuestasCorrectas = correctas,
//            pistasUsadas = pistasUsadas,
//            dificultad = dificultad
//        )
//    }
}