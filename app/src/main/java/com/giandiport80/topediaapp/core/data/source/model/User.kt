package com.giandiport80.topediaapp.core.data.source.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int?,

    val name: String?,

    val email: String?,

    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any?,

    val phone: String?,

    val image: String?,

    @SerializedName("updated_at")
    val updatedAt: String?,

    @SerializedName("created_at")
    val createdAt: String?,

    val token: String?,
)
