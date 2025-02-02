package com.giandiport80.topediaapp.core.data.source.remote.response

import com.giandiport80.topediaapp.core.data.source.model.User

data class LoginResponse(
    val code: Int? = null,
    val message: String? = null,
    val data: User? = null
)