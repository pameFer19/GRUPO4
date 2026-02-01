package com.example.electronicazytron.model.entities

import androidx.room.*

@Dao
interface ProductoDao{
    @Query("SELECT * FROM productos ORDER BY codigo ASC")
    suspend fun listar(): List<Producto>

    @Query("SELECT * FROM productos WHERE codigo = :codigo LIMIT 1")
    suspend fun buscarPorCodigo(codigo: String): Producto?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertar(producto: Producto)

    @Update
    suspend fun actualizar(producto: Producto)

    @Delete
    suspend fun eliminar(producto: Producto)

    @Query("SELECT COUNT(*) FROM productos")
    suspend fun count(): Int
}