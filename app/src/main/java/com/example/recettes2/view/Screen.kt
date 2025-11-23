package com.example.recettes2.view

sealed class Screen(val route: String) {

    data object Categories : Screen("categories")

    data object Recettes : Screen("recettes/{category}") {
        fun createRoute(category: String) = "recettes/$category"
    }

    data object RecetteDetail : Screen("recette/{mealId}") {
        fun createRoute(mealId: String) = "recette/$mealId"
    }
}
