package com.giandiport80.topediaapp.core.data.source.model

data class Product(
    val id: Int? = null,
    val name: String?,
    val harga: Int?,
    val pengiriman: String?,
    val terjual: Int?,
    val rating: Double?,
    val discount: Int?,
    val grosir: Boolean = false,
    val image: Int?
)
