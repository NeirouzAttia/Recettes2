package com.example.recettes2.modelview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recettes2.database.DatabaseProvider
import com.example.recettes2.model.FavoriteRecette
import com.example.recettes2.repository.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = DatabaseProvider.getDatabase(app).favoriteDao()
    private val repo = FavoriteRepository(dao)

    val favorites = repo.favorites.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun toggleFavorite(recette: FavoriteRecette) {
        viewModelScope.launch {
            if (repo.isFavorite(recette.idMeal)) {
                repo.removeFavorite(recette)
            } else {
                repo.addFavorite(recette)
            }
        }
    }
    fun removeFavorite(recette: FavoriteRecette) {
        viewModelScope.launch {
            repo.removeFavorite(recette)
        }
    }
}
