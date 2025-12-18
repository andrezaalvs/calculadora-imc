package com.keila.calculadoradeimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost           // IMPORTANTE
import androidx.navigation.compose.composable        // IMPORTANTE
import androidx.navigation.compose.rememberNavController // IMPORTANTE
import com.keila.calculadoradeimc.data.AppDatabase
import com.keila.calculadoradeimc.ui.theme.CalculadoraDeIMCTheme
import com.keila.calculadoradeimc.view.CalculadoraViewModel
import com.keila.calculadoradeimc.view.CalculadoraViewModelFactory
import com.keila.calculadoradeimc.view.Home
import com.keila.calculadoradeimc.view.TelaHistorico

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(applicationContext)
        val dao = db.historicoDao()
        val factory = CalculadoraViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, factory)[CalculadoraViewModel::class.java]

        setContent {
            CalculadoraDeIMCTheme {
                // 1. Criar o controlador de navegação
                val navController = rememberNavController()

                // 2. Definir o Grafo de Navegação (As rotas)
                NavHost(
                    navController = navController,
                    startDestination = "home" // Começa pela tela principal
                ) {
                    // Rota da Home
                    composable("home") {
                        Home(
                            viewModel = viewModel,
                            onIrParaHistorico = {
                                // Quando clicar no botão, vai para a rota "historico"
                                navController.navigate("historico")
                            }
                        )
                    }

                    // Rota do Histórico
                    composable("historico") {
                        TelaHistorico(viewModel = viewModel)
                    }
                }
            }
        }
    }
}