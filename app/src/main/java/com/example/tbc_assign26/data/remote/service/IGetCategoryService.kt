package com.example.tbc_assign26.data.remote.service

import com.example.tbc_assign26.data.remote.model.CategoryDto
import retrofit2.Response
import retrofit2.http.GET

interface IGetCategoryService {
    @GET("6f722f19-3297-4edd-a249-fe765e8104db?search=title")
    suspend fun getCategory(): Response<List<CategoryDto>>
}