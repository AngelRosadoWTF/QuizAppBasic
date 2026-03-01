package Interfaces

import Models.RondaPregunta
import Models.ResultadoFinalModel
import com.example.quizappbasic.Difficulty

interface Calculadorapuntaje {
   fun calcular(rondas: List<RondaPregunta>, dificultad: Difficulty): ResultadoFinalModel
}