package com.example.recettes2.model

import kotlinx.serialization.Serializable

@Serializable
data class RecetteDetailResponse(
    val meals: List<RecetteDetail>
)

@Serializable
data class RecetteDetail(
    val idMeal: String,
    val strMeal: String,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String?,
    val strYoutube: String? = null
)
