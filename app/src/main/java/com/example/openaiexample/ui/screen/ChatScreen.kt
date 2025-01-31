package com.example.openaiexample.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.openaiexample.ui.viewmodels.OpenAIViewModel
import androidx.compose.ui.graphics.Color // Add this import

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(viewModel: OpenAIViewModel = hiltViewModel()) {
    val response by viewModel.response.collectAsState()
    var userMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Observe changes in the ViewModel and update the UI
    LaunchedEffect(response) {
        if (response.isNotEmpty() && response != "Error: null") {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            // Headline at the top
            TopAppBar(
                title = { Text("Chat with AI") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomBar = {
            // BottomAppBar
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = Color.Transparent // Remove background by setting it to transparent

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // User input field
                    BasicTextField(
                        value = userMessage,
                        onValueChange = { userMessage = it },
                        modifier = Modifier
                            .weight(1f) // Let it fill the available space
                            .border(1.dp, MaterialTheme.colorScheme.primary, shape = CircleShape) // Circular border
                            .padding(16.dp),
                        decorationBox = { innerTextField ->
                            if (userMessage.isEmpty()) {
                                Text("Talk to Ai", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)) // Hint text
                            }
                            innerTextField() // Actual text field content
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Spacer between input field and button

                    // Send button
                    Button(
                        onClick = {
                            if (userMessage.isNotEmpty()) {
                                isLoading = true
                                viewModel.getOpenAIResponse(userMessage)
                                userMessage = "" // Clear input field after sending
                            }
                        },
                        enabled = !isLoading
                    ) {
                        Text("Send")
                    }
                }
            }
        },
        content = { paddingValues ->
            // Main content with response display
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Use padding from Scaffold
            ) {
                // Display the response
                if (response.isNotEmpty()) {
                    Text("Response: $response", modifier = Modifier.padding(16.dp))
                }

                // Show loading indicator if waiting for response
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .align(Alignment.CenterHorizontally) // Aligns horizontally at the center
                    )
                }
            }
        }
    )
}