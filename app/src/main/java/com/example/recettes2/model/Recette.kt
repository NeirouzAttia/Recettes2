package com.example.recettes2.model

import kotlinx.serialization.Serializable

@Serializable
data class Recette(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

@Serializable
data class RecettesResponse(
    val meals: List<Recette>
)
