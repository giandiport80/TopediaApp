package com.giandiport80.topediaapp.core.data.source.remote.response

class BaseSingleResponse<T>(
    val code: Int? = null,
    val message: String? = null,
    val data: T? = null
)