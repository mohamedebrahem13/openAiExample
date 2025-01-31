package com.example.openaiexample.di

import com.example.openaiexample.data.repository.OpenAIRepositoryImpl
import com.example.openaiexample.data.repository.remote.OpenAIService
import com.example.openaiexample.domain.repository.remote.OpenAIRepository
import com.example.openaiexample.domain.intractor.FetchOpenAIResponseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideOpenAIRepository(api: OpenAIService): OpenAIRepository {
        return OpenAIRepositoryImpl(api)
    }
    @Provides
    fun provideFetchOpenAIResponseUseCase(
        openAIRepository: OpenAIRepository
    ): FetchOpenAIResponseUseCase {
        return FetchOpenAIResponseUseCase(openAIRepository)
    }
}
