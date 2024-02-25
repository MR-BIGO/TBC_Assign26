package com.example.tbc_assign26.domain.use_case

import com.example.tbc_assign26.data.common.Resource
import com.example.tbc_assign26.domain.model.Category
import com.example.tbc_assign26.domain.repository.IGetCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: IGetCategoryRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Category>>>{
        return repository.getCategory()
    }
}