package com.keila.calculadoradeimc.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historico_imc")
data class Historico(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val peso: Double,
    val altura: Double,
    val idade: Int,
    val sexo: String,
    val imc: Double,
    val classificacaoImc: String,
    val tmb: Double,
    val pesoIdeal: Double,
    val necessidadeCalorica: Double,
    val ingestaoAgua: Double, // Litros de água
    val dataCadastro: Long = System.currentTimeMillis()
)