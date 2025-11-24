package com.example.recettes2.modelview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recettes2.model.RecetteDetail
import com.example.recettes2.network.RecetteApi
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class RecetteDetailViewModel : ViewModel() {

    private val _recette = mutableStateOf<RecetteDetail?>(null)
    val recette: State<RecetteDetail?> = _recette

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun loadDetail(id: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _recette.value = RecetteApi.getRecetteDetail(id)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}
