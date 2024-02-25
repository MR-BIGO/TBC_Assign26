package com.example.tbc_assign26.data.repository.remote

import com.example.tbc_assign26.data.common.Resource
import com.example.tbc_assign26.data.remote.mapper.mapListToDomain
import com.example.tbc_assign26.data.remote.mapper.toDomain
import com.example.tbc_assign26.data.remote.service.IGetCategoryService
import com.example.tbc_assign26.data.remote.util.HandleResponse
import com.example.tbc_assign26.domain.model.Category
import com.example.tbc_assign26.domain.repository.IGetCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryRepositoryImpl @Inject constructor(
    private val service: IGetCategoryService,
    private val handler: HandleResponse
) : IGetCategoryRepository {
    override suspend fun getCategory(): Flow<Resource<List<Category>>> {
        return handler.safeApiCall { service.getCategory() }.mapListToDomain { it.toDomain() }
    }
}