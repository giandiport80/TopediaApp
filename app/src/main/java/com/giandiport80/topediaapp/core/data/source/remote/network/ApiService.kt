package com.giandiport80.topediaapp.core.data.source.remote.network

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST
    suspend fun login(
        @Body user: Any
    ): Response<RequestBody>
}