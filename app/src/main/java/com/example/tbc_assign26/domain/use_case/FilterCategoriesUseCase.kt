package com.example.tbc_assign26.domain.use_case

import android.util.Log
import com.example.tbc_assign26.data.common.Resource
import com.example.tbc_assign26.domain.model.Category
import com.example.tbc_assign26.domain.repository.IGetCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterCategoriesUseCase @Inject constructor(private val repository: IGetCategoryRepository) {

    suspend operator fun invoke(keyword: String): Flow<Resource<List<Category>>> {
        return flow {
            Log.d("did i make it here?", "i did flow")
            repository.getCategory().map { resource ->
                when (resource) {
                    is Resource.Success -> {
                        Log.d("did i make it here?", "i did success")
                        emit(Resource.Success(filterItems(resource.data, keyword)))
                    }

                    is Resource.Loading -> {
                        Log.d("did i make it here?", "i did loading")
                        emit(resource)
                    }

                    is Resource.Error -> {
                        Log.d("did i make it here?", "i did error")
                        emit(resource)
                    }
                }
            }
        }
    }

    private fun filterItems(categories: List<Category>, keyword: String): List<Category> {
        Log.d("did i make it here?", "i did filter")
        return categories.filter { it.name.contains(keyword) }
    }
}