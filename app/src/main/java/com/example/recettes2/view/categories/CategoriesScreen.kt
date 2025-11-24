package com.example.recettes2.view.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.recettes2.modelview.CategoryViewModel
import com.example.recettes2.view.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(navController: NavController) {

    val viewModel = remember { CategoryViewModel() }

    val categories by viewModel.categories
    val loading by viewModel.loading
    val error by viewModel.error

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.Favorites.route)
                }
            ) {
                Icon(Icons.Default.Favorite, contentDescription = "Favoris")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Text(
                "Catégories",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            when {
                loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                error != null -> {
                    Text("Erreur : $error", color = MaterialTheme.colorScheme.error)
                }

                else -> {
                    LazyColumn {
                        items(categories) { category ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(Screen.Recettes.createRoute(category.strCategory))
                                    }
                                    .padding(12.dp)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(category.strCategoryThumb),
                                    contentDescription = null,
                                    modifier = Modifier.size(80.dp)
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    category.strCategory,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
