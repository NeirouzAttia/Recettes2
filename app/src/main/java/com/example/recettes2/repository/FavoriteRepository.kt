package com.example.recettes2.repository

import com.example.recettes2.database.FavoriteDao
import com.example.recettes2.model.FavoriteRecette

class FavoriteRepository(private val dao: FavoriteDao) {

    val favorites = dao.getFavorites()

    suspend fun addFavorite(recette: FavoriteRecette) {
        dao.addFavorite(recette)
    }

    suspend fun removeFavorite(recette: FavoriteRecette) {
        dao.removeFavorite(recette)
    }

    suspend fun isFavorite(id: String): Boolean {
        return dao.isFavorite(id)
    }
}
