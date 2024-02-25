package com.example.tbc_assign26.di

import com.example.tbc_assign26.data.remote.service.IGetCategoryService
import com.example.tbc_assign26.data.remote.util.HandleResponse
import com.example.tbc_assign26.data.repository.remote.GetCategoryRepositoryImpl
import com.example.tbc_assign26.domain.repository.IGetCategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }

    @Provides
    @Singleton
    fun provideGetCategoryRepository(
        service: IGetCategoryService,
        handleResponse: HandleResponse
    ): IGetCategoryRepository {
        return GetCategoryRepositoryImpl(service, handleResponse)
    }
}