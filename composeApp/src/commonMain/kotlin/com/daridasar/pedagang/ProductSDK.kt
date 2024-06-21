package com.daridasar.pedagang

import com.daridasar.pedagang.entity.Product
import com.daridasar.pedagang.network.ProductApi

class ProductSDK(databaseDriverFactory: DatabaseDriverFactory, val api: ProductApi) {
    private val database = Database(databaseDriverFactory)
    
    @Throws(Exception::class)
    suspend fun getProducts(forceReload: Boolean): List<Product> {
        val cachedProducts = database.getAllProducts()
        return if (cachedProducts.isNotEmpty() && !forceReload) {
            cachedProducts
        } else {
            api.getAllProducts().also {
                database.clearAndCreateProducts(it)
            }
        }
    }
}