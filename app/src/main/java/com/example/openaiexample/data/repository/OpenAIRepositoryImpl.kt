package com.example.openaiexample.data.repository

import com.example.openaiexample.data.models.Message
import com.example.openaiexample.data.models.OpenAIRequest
import com.example.openaiexample.data.repository.remote.OpenAIService
import com.example.openaiexample.domain.OpenAIRepository
import javax.inject.Inject

class OpenAIRepositoryImpl @Inject constructor(
    private val api: OpenAIService
) : OpenAIRepository {

    override suspend fun fetchResponse(userMessage: String): String {
        return try {
            val messages = listOf(Message("user", userMessage))
            val response = api.getChatCompletion(OpenAIRequest(messages = messages))
            response.choices.firstOrNull()?.message?.content ?: "No response"
        } catch (e: Exception) {
            "Error: ${e.localizedMessage}"
        }
    }
}