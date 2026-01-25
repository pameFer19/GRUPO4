package com.example.electronicazytron.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users ORDER BY nombre ASC")
    fun getAllUsers(): Flow<List<User>>
}