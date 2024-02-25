package com.example.tbc_assign26.presentation.mapper

import com.example.tbc_assign26.domain.model.Category
import com.example.tbc_assign26.presentation.model.CategoryPresentation

fun Category.fromDomain(): CategoryPresentation{
    return CategoryPresentation(
        id = id,
        name = name,
        children = children
    )
}