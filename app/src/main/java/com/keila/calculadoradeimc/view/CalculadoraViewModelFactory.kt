package com.keila.calculadoradeimc.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keila.calculadoradeimc.data.HistoricoDao

class CalculadoraViewModelFactory(private val dao: HistoricoDao) : ViewModelProvider.Factory {

    // Essa função ensina o Android a criar o ViewModel injetando o DAO
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculadoraViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CalculadoraViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}