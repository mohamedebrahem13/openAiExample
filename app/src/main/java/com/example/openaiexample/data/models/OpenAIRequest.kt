package com.example.openaiexample.data.models

data class OpenAIRequest(
    val model: String = "gpt-4o-mini",
    val messages: List<Message>
)