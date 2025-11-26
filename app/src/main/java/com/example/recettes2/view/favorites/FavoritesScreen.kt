package com.example.recettes2.view.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recettes2.modelview.FavoritesViewModel
import com.example.recettes2.view.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController) {

    val favVm: FavoritesViewModel = viewModel()
    val favorites by favVm.favorites.collectAsState()

    var searchText by remember { mutableStateOf("") }

    val filteredFavorites = favorites.filter {
        it.strMeal.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Mes Favoris ❤️")
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            placeholder = { Text("Rechercher une recette...") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { paddingValues ->

        if (filteredFavorites.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("Aucun favori pour l'instant 👀")
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(filteredFavorites) { fav ->

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.RecetteDetail.route + "/${fav.idMeal}")
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(modifier = Modifier.weight(1f)) {
                            AsyncImage(
                                model = fav.strMealThumb,
                                contentDescription = fav.strMeal,
                                modifier = Modifier.size(80.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = fav.strMeal,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        IconButton(onClick = { favVm.removeFavorite(fav) }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Supprimer",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}
