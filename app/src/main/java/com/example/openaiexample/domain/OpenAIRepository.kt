package com.example.openaiexample.domain


interface OpenAIRepository {
    suspend fun fetchResponse(userMessage: String): String
}