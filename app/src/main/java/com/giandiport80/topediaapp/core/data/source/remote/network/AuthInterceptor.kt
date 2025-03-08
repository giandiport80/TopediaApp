package com.giandiport80.topediaapp.core.data.source.remote.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "H7Yiuntc9ZyQmBVCNApgqLUGW62SK3R4hOJdfI8wvaTxbPsjoFDr5eEzl1Mk"
        val requestBuilder = chain.request().newBuilder().header("token", token)

        return chain.proceed(requestBuilder.build())
    }
}