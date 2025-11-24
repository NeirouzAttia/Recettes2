package com.example.recettes2.network

import com.example.recettes2.model.RecettesResponse
import com.example.recettes2.model.RecetteDetail
import com.example.recettes2.model.RecetteDetailResponse
import io.ktor.client.call.*
import io.ktor.client.request.*

object RecetteApi {

    suspend fun getRecettesByCategory(category: String): RecettesResponse {
        return KtorClient.httpClient
            .get("https://www.themealdb.com/api/json/v1/1/filter.php?c=$category")
            .body()
    }

    suspend fun getRecetteDetail(id: String): RecetteDetail {
        return KtorClient.httpClient
            .get("https://www.themealdb.com/api/json/v1/1/lookup.php?i=$id")
            .body<RecetteDetailResponse>()
            .meals
            .first()
    }
}
