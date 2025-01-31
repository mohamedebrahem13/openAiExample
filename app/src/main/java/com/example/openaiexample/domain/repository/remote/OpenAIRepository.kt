package com.example.openaiexample.domain.repository.remote


interface OpenAIRepository {
    suspend fun fetchResponse(userMessage: String): String
}