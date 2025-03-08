package com.giandiport80.topediaapp.core.data.source.model

import com.google.gson.annotations.SerializedName

data class UserRole(
    val id: String? = null,

    @SerializedName("is_admin")
    val isAdmin: Boolean? = false
)

