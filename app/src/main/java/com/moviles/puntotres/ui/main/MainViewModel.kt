package com.moviles.puntotres.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import kotlin.math.pow

class MainViewModel : ViewModel() {
    private val df = DecimalFormat("#.#")

    val imc : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val message : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun calculateIMC(
        peso: Double,
        altura: Double,
        string: String,
        string1: String,
        string2: String,
        string3: String
    ) {
        val aux = peso/ altura.pow(2)
        imc.value = df.format(aux)
        if (aux< 18.5) {
            message.value = string
        }
        if (aux>=18.5 && aux<24.9) message.value = string1
        if (aux>=24.9 && aux<29.9) message.value = string2
        if (aux>=29.9) message.value = string3
    }

}