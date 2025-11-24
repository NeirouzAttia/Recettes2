package com.example.recettes2.repository

import com.example.recettes2.database.FavoriteDao
import com.example.recettes2.model.FavoriteRecette

class FavoriteRepository(private val dao: FavoriteDao) {

    val favorites = dao.getFavorites()

    suspend fun addFavorite(fav: FavoriteRecette) {
        dao.addFavorite(fav)
    }

    suspend fun removeFavorite(fav: FavoriteRecette) {
        dao.removeFavorite(fav)
    }

    suspend fun isFavorite(id: String): Boolean {
        return dao.isFavorite(id)
    }
}
