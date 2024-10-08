package com.wim.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("HeaderName", "HeaderValue")
            .build()
        return chain.proceed(request)
    }
}