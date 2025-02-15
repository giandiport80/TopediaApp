package com.giandiport80.topediaapp.core.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class CreateTokoRequest(
    @SerializedName("user_id")
    val userId: Int,

    val nama: String,

    val kota: String,

    )
