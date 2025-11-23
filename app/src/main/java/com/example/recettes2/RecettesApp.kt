package com.example.recettes2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
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

        composable(
            route = Screen.Recettes.route + "/{category}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            RecettesScreen(navController, category)
        }

        composable(
            route = Screen.RecetteDetail.route + "/{mealId}",
            arguments = listOf(
                navArgument("mealId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("mealId") ?: ""
            RecetteDetailScreen(id)
        }
    }
}
