package com.example.openaiexample.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openaiexample.domain.intractor.FetchOpenAIResponseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenAIViewModel @Inject constructor(
    private val fetchOpenAIResponseUseCase: FetchOpenAIResponseUseCase
) : ViewModel() {

    private val _response = MutableStateFlow("")
    val response: StateFlow<String> get() = _response

    fun getOpenAIResponse(userMessage: String) {
        viewModelScope.launch {
            try {
                _response.value = fetchOpenAIResponseUseCase.execute(userMessage)
            } catch (e: Exception) {
                _response.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}