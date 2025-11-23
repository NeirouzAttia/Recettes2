package com.example.recettes2.view

sealed class Screen(val route: String) {

    object Categories : Screen("categories")

    object Recettes : Screen("recettes") {
        fun createRoute(category: String): String {
            return "recettes/$category"
        }
    }

    object RecetteDetail : Screen("recetteDetail") {
        fun createRoute(mealId: String): String {
            return "recetteDetail/$mealId"
        }
    }
}
