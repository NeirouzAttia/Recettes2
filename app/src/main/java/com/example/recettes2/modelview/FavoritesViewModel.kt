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
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
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
}
