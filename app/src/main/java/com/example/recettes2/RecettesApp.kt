package com.example.recettes2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.recettes2.view.Screen
import com.example.recettes2.view.categories.CategoriesScreen
import com.example.recettes2.view.detail.RecetteDetailScreen
import com.example.recettes2.view.recettes.RecettesScreen

@Composable
fun RecettesApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Categories.route
    ) {

        composable(Screen.Categories.route) {
            CategoriesScreen(navController)
        }

        composable(Screen.Recettes.route) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            RecettesScreen(navController, category)
        }

        composable(Screen.RecetteDetail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("mealId") ?: ""
            RecetteDetailScreen(id)
        }
    }
}
