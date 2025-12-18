package com.keila.calculadoradeimc.domain

import com.keila.calculadoradeimc.view.FatorAtividade

object Calculations {

    fun calcularImc(peso: Double, altura: Double): Double {
        val alturaMetros = altura / 100
        return peso / (alturaMetros * alturaMetros)
    }

    fun classificarImc(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Peso normal"
            imc < 30.0 -> "Sobrepeso"
            imc < 35.0 -> "Obesidade Grau I"
            imc < 40.0 -> "Obesidade Grau II"
            else -> "Obesidade Grau III"
        }
    }

    fun calcularPesoIdeal(altura: Double, isHomem: Boolean): Double {
        val alturaPolegadas = altura / 2.54
        val excessoPolegadas = if (alturaPolegadas > 60.0) alturaPolegadas - 60.0 else 0.0
        return if (isHomem) 50.0 + (2.3 * excessoPolegadas) else 45.5 + (2.3 * excessoPolegadas)
    }

    // 1. TMB - Gasto em repouso com o peso ATUAL
    fun calcularTmb(peso: Double, altura: Double, idade: Int, isHomem: Boolean): Double {
        return if (isHomem) {
            (10 * peso) + (6.25 * altura) - (5 * idade) + 5
        } else {
            (10 * peso) + (6.25 * altura) - (5 * idade) - 161
        }
    }

    // 2. MANTER O NOME: necessidadeCalorica
    // Mas agora a lógica usa o Peso Ideal para calcular a meta de calorias
    fun calcularNecessidadeCalorica(
        altura: Double,
        idade: Int,
        isHomem: Boolean,
        atividade: FatorAtividade
    ): Double {
        val pIdeal = calcularPesoIdeal(altura, isHomem)

        // Calculamos a TMB que a pessoa teria se estivesse no peso ideal
        val tmbIdeal = if (isHomem) {
            (10 * pIdeal) + (6.25 * altura) - (5 * idade) + 5
        } else {
            (10 * pIdeal) + (6.25 * altura) - (5 * idade) - 161
        }

        // Retorna as calorias para atingir/manter esse peso ideal conforme o exercício
        return tmbIdeal * atividade.fator
    }

    fun calcularAgua(peso: Double): Double = (peso * 35) / 1000
}