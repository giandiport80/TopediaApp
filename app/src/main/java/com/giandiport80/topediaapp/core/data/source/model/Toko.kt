package com.giandiport80.topediaapp.core.data.source.model

import com.google.gson.annotations.SerializedName

data class Toko(
    val id: Int? = null,

    val nama: String? = null,

    val kota: String? = null,

    val image: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null
)
