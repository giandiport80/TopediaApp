package com.giandiport80.topediaapp.core.data.source.remote.response

data class BaseResponse<T>(
    val code: Int? = null,
    val message: String? = null,
    val data: T? = null
)
