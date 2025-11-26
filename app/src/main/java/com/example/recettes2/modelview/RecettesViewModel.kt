package com.example.recettes2.modelview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recettes2.model.Recette
import com.example.recettes2.network.RecetteApi
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class RecettesViewModel : ViewModel() {

    private val _recettes = mutableStateOf<List<Recette>>(emptyList())
    val recettes: State<List<Recette>> = _recettes

    // Liste complète avant filtrage
    private val allRecettes = mutableListOf<Recette>()

    fun loadRecettes(category: String) {
        viewModelScope.launch {
            try {
                val response = RecetteApi.getRecettesByCategory(category)
                allRecettes.clear()
                allRecettes.addAll(response.meals)
                _recettes.value = allRecettes
            } catch (e: Exception) {
                _recettes.value = emptyList()
            }
        }
    }

    fun search(query: String) {
        _recettes.value =
            if (query.isBlank()) allRecettes
            else allRecettes.filter {
                it.strMeal.contains(query, ignoreCase = true)
            }
    }
}
