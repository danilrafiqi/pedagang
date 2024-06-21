package com.daridasar.pedagang
import com.daridasar.pedagang.entity.Product

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = PedagangDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.productQueries


    internal fun getAllProducts(): List<Product> {
        return dbQuery.selectAllProducts(::mapProductSelecting).executeAsList()
    }

    private fun mapProductSelecting(
        id: Long,
        name: String,
        price: Long
    ): Product {
        return Product(
            id = id.toInt(),
            name = name,
            price = price.toInt()
        )
    }

    internal fun clearAndCreateProducts(products: List<Product>) {
        dbQuery.transaction {
            dbQuery.removeAllProducts()
            products.forEach { product ->
                dbQuery.insertProduct(
                    price = product.price.toLong(),
                    name = product.name
                )
            }
        }
    }
}