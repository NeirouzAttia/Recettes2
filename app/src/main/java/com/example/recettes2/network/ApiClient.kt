package com.example.recettes2.network

import com.example.recettes2.model.Category
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

object ApiClient {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    @Serializable
    data class CategoriesResponse(
        val categories: List<Category>
    )

    suspend fun getCategories(): List<Category> {
        val response: CategoriesResponse =
            client.get("https://www.themealdb.com/api/json/v1/1/categories.php").body()

        return response.categories
    }
}
