package com.keila.calculadoradeimc.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoricoDao {

    @Insert
    suspend fun gravar(historico: Historico)

    @Query("SELECT * FROM historico_imc ORDER BY dataCadastro DESC")
    fun listarTodos(): Flow<List<Historico>>

    @Delete
    suspend fun excluir(historico: Historico)
}