package com.giandiport80.topediaapp.core.data.source.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int? = null,
    val name: String?,
    val harga: Int? = null,
    val price: Int?,

    @SerializedName("images")
    val imageReal: String? = null,

    @SerializedName("toko_id")
    val tokoId: Int? = null,

    val description: String? = null,
    val weight: Int? = null,
    val stock: Int? = null,
    val isActive: Boolean? = null,

    val pengiriman: String? = null,
    val sold: Int? = null,
    val rating: Double? = null,
    val discount: Int? = null,
    val grosir: Boolean = false,

    @SerializedName("image_dummy")
    val image: Int? = null,
)
