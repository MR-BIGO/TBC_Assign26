package com.example.tbc_assign26.domain.repository

import com.example.tbc_assign26.data.common.Resource
import com.example.tbc_assign26.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface IGetCategoryRepository {

    suspend fun getCategory(): Flow<Resource<List<Category>>>
}