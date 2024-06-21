package com.daridasar.pedagang.network

import com.daridasar.pedagang.entity.Product
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ProductApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }
    
    suspend fun getAllProducts(): List<Product> {
        return httpClient.get("http://localhost:3000/products").body()
    }
}