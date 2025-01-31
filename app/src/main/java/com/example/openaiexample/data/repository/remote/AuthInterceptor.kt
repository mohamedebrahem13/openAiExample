package com.example.openaiexample.data.repository.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import com.example.openaiexample.BuildConfig

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = BuildConfig.OPENAI_API_KEY
        Log.d("AuthInterceptor", "API Key: $apiKey") // Log to check the key
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        return chain.proceed(request)

    }

}
