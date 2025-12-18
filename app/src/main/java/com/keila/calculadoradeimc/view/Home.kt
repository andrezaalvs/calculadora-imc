package com.keila.calculadoradeimc.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keila.calculadoradeimc.domain.Calculations
import com.keila.calculadoradeimc.ui.theme.Blue
import com.keila.calculadoradeimc.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    viewModel: CalculadoraViewModel? = null,
    onIrParaHistorico: () -> Unit
) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(true) }
    var activityExpanded by remember { mutableStateOf(false) }
    var selectedActivity by remember { mutableStateOf(FatorAtividade.SEDENTARIO) }

    var resultMessage by remember { mutableStateOf("") }
    var currentClassification by remember { mutableStateOf("") }
    var textFieldError by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de Saúde",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Blue,
            modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
        )

        // Campos de Texto
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
            isError = textFieldError
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Altura (cm)") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
            isError = textFieldError
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Idade") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
            isError = textFieldError
        )

        // Sexo
        Row(modifier = Modifier.padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = isMale, onClick = { isMale = true })
            Text("Masc", color = Blue)
            Spacer(modifier = Modifier.width(20.dp))
            RadioButton(selected = !isMale, onClick = { isMale = false })
            Text("Fem", color = Blue)
        }

        // Dropdown de Atividade
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
            OutlinedTextField(
                value = selectedActivity.descricao,
                onValueChange = {},
                readOnly = true,
                label = { Text("Atividade Física") },
                trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledBorderColor = Blue,
                    disabledLabelColor = Blue
                )
            )
            Box(modifier = Modifier.matchParentSize().clickable { activityExpanded = true })

            DropdownMenu(expanded = activityExpanded, onDismissRequest = { activityExpanded = false }) {
                FatorAtividade.entries.forEach { atividade ->
                    DropdownMenuItem(
                        text = { Text(atividade.descricao) },
                        onClick = {
                            selectedActivity = atividade
                            activityExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botão Calcular (AQUI CORRIGIMOS OS ERROS DA LINHA 146)
        Button(
            onClick = {
                focusManager.clearFocus()
                if (weight.isNotEmpty() && height.isNotEmpty() && age.isNotEmpty()) {
                    textFieldError = false
                    val p = weight.replace(",", ".").toDoubleOrNull() ?: 0.0
                    val a = height.replace(",", ".").toDoubleOrNull() ?: 0.0
                    val i = age.toIntOrNull() ?: 0

                    if (p > 0 && a > 0) {
                        val imc = Calculations.calcularImc(p, a)
                        val classif = Calculations.classificarImc(imc)
                        val tmb = Calculations.calcularTmb(p, a, i, isMale)
                        val pIdeal = Calculations.calcularPesoIdeal(a, isMale)

                        // CORREÇÃO: Usando nomes dos parâmetros para evitar erro de tipo
                        val calMeta = Calculations.calcularNecessidadeCalorica(
                            altura = a,
                            idade = i,
                            isHomem = isMale,
                            atividade = selectedActivity
                        )

                        val agua = Calculations.calcularAgua(p)

                        currentClassification = classif
                        viewModel?.calcularESalvar(p, a, i, isMale, selectedActivity)

                        resultMessage = """
                            IMC: ${String.format("%.2f", imc)} ($classif)
                            TMB: ${String.format("%.0f", tmb)} kcal
                            Peso Ideal: ${String.format("%.1f", pIdeal)} kg
                            Meta Calorias: ${String.format("%.0f", calMeta)} kcal
                            Água: ${String.format("%.2f", agua)} L
                        """.trimIndent()
                    }
                } else {
                    textFieldError = true
                    resultMessage = "Preencha todos os campos!"
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Blue)
        ) {
            Text("CALCULAR E SALVAR", color = White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onIrParaHistorico,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
        ) {
            Text("VER HISTÓRICO", color = Blue)
        }

        // Resultado Colorido
        if (resultMessage.isNotEmpty()) {
            val corResultado = if (textFieldError) Color.Red else obterCorImc(currentClassification)
            Card(
                modifier = Modifier.padding(20.dp),
                colors = CardDefaults.cardColors(containerColor = corResultado.copy(alpha = 0.1f)),
                border = androidx.compose.foundation.BorderStroke(1.dp, corResultado)
            ) {
                Text(
                    text = resultMessage,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = corResultado,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
} // <--- AQUI FECHA A FUNÇÃO HOME CORRETAMENTE

// AS FUNÇÕES E ENUMS ABAIXO DEVEM FICAR FORA DA FUNÇÃO HOME
fun obterCorImc(classificacao: String): Color {
    return when {
        classificacao.contains("Abaixo") -> Color(0xFF03A9F4)
        classificacao.contains("normal") -> Color(0xFF2E7D32)
        classificacao.contains("Sobrepeso") -> Color(0xFFFFB300)
        classificacao.contains("Obesidade") -> Color.Red
        else -> Color.Gray
    }
}

enum class FatorAtividade(val fator: Double, val descricao: String) {
    SEDENTARIO(1.2, "Sedentário"),
    LEVE(1.375, "Leve (1-3 dias)"),
    MODERADO(1.55, "Moderado (3-5 dias)"),
    INTENSO(1.725, "Intenso (6-7 dias)")
}