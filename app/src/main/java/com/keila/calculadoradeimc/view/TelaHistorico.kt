package com.keila.calculadoradeimc.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keila.calculadoradeimc.data.Historico
import com.keila.calculadoradeimc.ui.theme.Blue
import com.keila.calculadoradeimc.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaHistorico(viewModel: CalculadoraViewModel) {
    val historicoLista by viewModel.listaHistorico.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meu Histórico de Saúde") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(White)
        ) {
            if (historicoLista.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Nenhum registro encontrado.", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(historicoLista) { item ->
                        CardHistorico(item)
                    }
                }
            }
        }
    }
}

@Composable
fun CardHistorico(item: Historico) {
    val dataFormatada = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(item.dataCadastro))

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Cabeçalho: Data e IMC
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = dataFormatada, fontSize = 12.sp, color = Color.Gray)
                Text(
                    text = "IMC: ${String.format("%.2f", item.imc)}",
                    fontWeight = FontWeight.Bold,
                    color = Blue
                )
            }

            Text(
                text = item.classificacaoImc,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp, color = Color.LightGray)

            // --- MEDIDAS NOVAS ADICIONADAS AQUI ---
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Peso: ${item.peso}kg", fontSize = 13.sp)
                Text(text = "Altura: ${item.altura}cm", fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Peso Ideal e Água
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Peso Ideal: ${String.format("%.1f", item.pesoIdeal)}kg", fontSize = 13.sp, color = Color(0xFF2E7D32))
                Text(text = "Água: ${String.format("%.2f", item.ingestaoAgua)}L", fontSize = 13.sp, color = Blue)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Calorias (TMB e Gasto Diário)
            Text(
                text = "Metabolismo Basal: ${String.format("%.0f", item.tmb)} kcal",
                fontSize = 13.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Necessidade Diária: ${String.format("%.0f", item.necessidadeCalorica)} kcal",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFD32F2F)
            )
        }
    }
}