package com.giandiport80.topediaapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TokoResponse(
    val id: Int? = null,

    @SerializedName("user_id")
    val userId: Int? = null,

    val nama: String? = null,

    val kota: String? = null,
)
