package com.example.recettes2.view.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recettes2.modelview.RecetteDetailViewModel

@Composable
fun RecetteDetailScreen(mealId: String) {

    val vm: RecetteDetailViewModel = viewModel()

    LaunchedEffect(mealId) {
        vm.loadDetail(mealId)
    }

    val recette = vm.recette.value
    val loading = vm.loading.value
    val error = vm.error.value

    when {
        loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Text(
                "Erreur : $error",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }

        recette != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {

                AsyncImage(
                    model = recette.strMealThumb,
                    contentDescription = recette.strMeal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    recette.strMeal,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Catégorie : ${recette.strCategory}")
                Text("Origine : ${recette.strArea}")

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Instructions :",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(recette.strInstructions ?: "Aucune instruction")

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = {
                    // OUVRIR YOUTUBE ?
                }) {
                    Text("Voir la vidéo YouTube")
                }
            }
        }
    }
}
