package com.example.recettes2.network

import com.example.recettes2.model.CategoriesResponse
import io.ktor.client.call.*
import io.ktor.client.request.*

object CategoryApi {

    suspend fun getCategories(): CategoriesResponse {
        return KtorClient.httpClient
            .get("https://www.themealdb.com/api/json/v1/1/categories.php")
            .body()
    }
}
