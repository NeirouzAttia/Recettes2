package com.example.recettes2.view.recettes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recettes2.model.FavoriteRecette
import com.example.recettes2.modelview.FavoritesViewModel
import com.example.recettes2.modelview.RecettesViewModel
import com.example.recettes2.view.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecettesScreen(
    navController: NavHostController,
    category: String
) {
    val vm: RecettesViewModel = viewModel()
    val favVm: FavoritesViewModel = viewModel()

    // Charger les recettes
    LaunchedEffect(category) {
        vm.loadRecettes(category)
    }

    val recettes by vm.recettes
    val favorites by favVm.favorites.collectAsState()

    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Recettes - $category")
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = {
                                searchText = it
                                vm.search(it)
                            },
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(recettes) { recette ->

                val isFavorite = favorites.any { it.idMeal == recette.idMeal }

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    navController.navigate(
                                        Screen.RecetteDetail.route + "/${recette.idMeal}"
                                    )
                                }
                        ) {
                            AsyncImage(
                                model = recette.strMealThumb,
                                contentDescription = recette.strMeal,
                                modifier = Modifier.size(80.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(text = recette.strMeal)
                        }

                        IconButton(
                            onClick = {
                                val fav = FavoriteRecette(
                                    idMeal = recette.idMeal,
                                    strMeal = recette.strMeal,
                                    strMealThumb = recette.strMealThumb
                                )
                                favVm.toggleFavorite(fav)
                            }
                        ) {
                            Icon(
                                imageVector =
                                    if (isFavorite) Icons.Filled.Favorite
                                    else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favori",
                                tint =
                                    if (isFavorite) MaterialTheme.colorScheme.error
                                    else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}
