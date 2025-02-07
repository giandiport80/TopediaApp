package com.giandiport80.topediaapp.core.data.source.remote.request

class UpdateProfileRequest(
    val id: Int,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
) {
}