package com.example.recettes2.database

import androidx.room.*
import com.example.recettes2.model.FavoriteRecette
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<FavoriteRecette>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteRecette)

    @Delete
    suspend fun removeFavorite(favorite: FavoriteRecette)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE idMeal = :id)")
    suspend fun isFavorite(id: String): Boolean
}
