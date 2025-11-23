package com.example.recettes2.view.recettes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recettes2.view.Screen

@Composable
fun RecettesScreen(navController: NavController, category: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Recettes de la catégorie : $category", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate(Screen.RecetteDetail.createRoute("52772"))
        }) {
            Text("Voir recette 52772 (démo)")
        }
    }
}
