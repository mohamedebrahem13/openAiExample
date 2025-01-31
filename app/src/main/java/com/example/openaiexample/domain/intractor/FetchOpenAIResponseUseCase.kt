package com.example.openaiexample.domain.intractor

import com.example.openaiexample.domain.OpenAIRepository
import javax.inject.Inject

class FetchOpenAIResponseUseCase @Inject constructor(
    private val openAIRepository: OpenAIRepository
) {
    suspend fun execute(userMessage: String): String {
        return openAIRepository.fetchResponse(userMessage)
    }
}