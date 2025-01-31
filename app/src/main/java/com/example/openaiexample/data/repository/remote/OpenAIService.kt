package com.example.openaiexample.data.repository.remote

import com.example.openaiexample.data.models.OpenAIRequest
import com.example.openaiexample.data.models.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAIService {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    suspend fun getChatCompletion(@Body request: OpenAIRequest): OpenAIResponse
}