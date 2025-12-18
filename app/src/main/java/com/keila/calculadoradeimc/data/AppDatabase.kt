package com.keila.calculadoradeimc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Define quais tabelas existem (aqui só tem a Historico) e a versão do banco
@Database(entities = [Historico::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Conecta com o DAO que criamos no passo 3
    abstract fun historicoDao(): HistoricoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Função para pegar a conexão com o banco
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "calculadora_imc_database" // Nome do arquivo do banco no celular
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}