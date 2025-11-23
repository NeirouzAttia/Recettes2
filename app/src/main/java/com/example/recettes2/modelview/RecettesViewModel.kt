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

    fun loadRecettes(category: String) {
        viewModelScope.launch {
            try {
                val response = RecetteApi.getRecettesByCategory(category)
                _recettes.value = response.meals
            } catch (e: Exception) {
                _recettes.value = emptyList()
            }
        }
    }
}
