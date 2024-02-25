package com.example.tbc_assign26.data.remote.mapper

import com.example.tbc_assign26.data.remote.model.CategoryDto
import com.example.tbc_assign26.domain.model.Category

fun CategoryDto.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        children = children.size
    )
}