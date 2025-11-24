package com.example.recettes2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteRecette(
    @PrimaryKey val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)
