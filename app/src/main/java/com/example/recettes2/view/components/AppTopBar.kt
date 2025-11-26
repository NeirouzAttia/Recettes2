package com.example.recettes2.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    navController: NavController? = null,
    title: String,
    onSearch: ((String) -> Unit)? = null
) {
    var searchText by remember { mutableStateOf("") }

    TopAppBar(
        title = {
            Column {
                Text(title)

                if (onSearch != null) {
                    OutlinedTextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            onSearch(it)
                        },
                        placeholder = { Text("Rechercher...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    )
                }
            }
        },
        navigationIcon = {
            if (navController != null) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Retour"
                    )
                }
            }
        }
    )
}
