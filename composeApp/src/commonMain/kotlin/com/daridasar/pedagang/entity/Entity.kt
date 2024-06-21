package com.daridasar.pedagang.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id")
    val id: Int,
    @SerialName("price")
    val price: Int,
    @SerialName("name")
    val name: String,
) {

}