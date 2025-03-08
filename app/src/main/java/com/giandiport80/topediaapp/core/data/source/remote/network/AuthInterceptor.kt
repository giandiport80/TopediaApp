package com.giandiport80.topediaapp.core.data.source.remote.network

import com.giandiport80.topediaapp.util.Prefs
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = Prefs.token
        val requestBuilder = chain.request().newBuilder().header("token", token)

        return chain.proceed(requestBuilder.build())
    }
}