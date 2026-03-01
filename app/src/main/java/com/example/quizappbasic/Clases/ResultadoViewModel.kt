package com.example.quizappbasic.Clases

import Models.ResultadoEstado
import androidx.lifecycle.ViewModel
//Guardamos el estado de la pantalla
class ResultadoViewModel : ViewModel() {
    var estado: ResultadoEstado = ResultadoEstado()
}