package com.example.recettes2.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

@Serializable
data class CategoriesResponse(
    val categories: List<Category>
)
