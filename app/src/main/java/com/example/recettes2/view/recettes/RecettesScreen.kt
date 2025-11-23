package com.example.recettes2.view.recettes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recettes2.modelview.RecettesViewModel
import com.example.recettes2.view.Screen

@Composable
fun RecettesScreen(
    navController: NavHostController,
    category: String
) {
    val vm: RecettesViewModel = viewModel()

    LaunchedEffect(category) {
        vm.loadRecettes(category)
    }

    LazyColumn {
        items(vm.recettes.value) { recette ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screen.RecetteDetail.route + "/${recette.idMeal}")
                    }
            ) {
                Row(modifier = Modifier.padding(12.dp)) {
                    AsyncImage(
                        model = recette.strMealThumb,
                        contentDescription = recette.strMeal,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = recette.strMeal)
                }
            }
        }
    }
}
