package com.keila.calculadoradeimc.view


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keila.calculadoradeimc.data.Historico
import com.keila.calculadoradeimc.data.HistoricoDao
import com.keila.calculadoradeimc.domain.Calculations
import kotlinx.coroutines.launch


class CalculadoraViewModel(private val dao: HistoricoDao) : ViewModel() {

    // Lista observável que carrega os dados do banco para a UI
    val listaHistorico = dao.listarTodos()

    fun calcularESalvar(
        peso: Double,
        altura: Double,
        idade: Int,
        sexoMasculino: Boolean,
        atividade: FatorAtividade
    ) {
        viewModelScope.launch {
            // 1. Cálculos de IMC e Classificação
            val imc = Calculations.calcularImc(peso, altura)
            val classificacao = Calculations.classificarImc(imc)

            // 2. TMB com o peso atual (Requisito do professor)
            val tmb = Calculations.calcularTmb(peso, altura, idade, sexoMasculino)

            // 3. Peso Ideal
            val pesoIdeal = Calculations.calcularPesoIdeal(altura, sexoMasculino)

            // 4. Necessidade Calórica (Lógica de Meta baseada no Peso Ideal)
            // Note que passamos altura, idade e sexo conforme a nova lógica do Calculations.kt
            val calorias = Calculations.calcularNecessidadeCalorica(
                altura = altura,
                idade = idade,
                isHomem = sexoMasculino,
                atividade = atividade
            )

            // 5. Ingestão de Água
            val agua = Calculations.calcularAgua(peso)

            // 6. Criando o objeto para salvar no Banco de Dados
            val novoRegistro = Historico(
                peso = peso,
                altura = altura,
                idade = idade,
                sexo = if (sexoMasculino) "M" else "F",
                imc = imc,
                classificacaoImc = classificacao,
                tmb = tmb,
                pesoIdeal = pesoIdeal,
                necessidadeCalorica = calorias,
                ingestaoAgua = agua
            )

            // 7. Gravação assíncrona no Room
            dao.gravar(novoRegistro)
        }
    }
}