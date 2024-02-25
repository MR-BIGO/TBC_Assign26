package com.example.tbc_assign26.data.remote.model

import com.squareup.moshi.Json

data class CategoryDto(
@Json(name = "id")
val id : String,
@Json(name = "name")
val name : String,
@Json(name = "children")
val children : List<CategoryDto>
)
