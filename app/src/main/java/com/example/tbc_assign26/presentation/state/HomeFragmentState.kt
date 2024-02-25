package com.example.tbc_assign26.presentation.state

import com.example.tbc_assign26.presentation.model.CategoryPresentation

data class HomeFragmentState (
    val categories: List<CategoryPresentation>? = null,
    val error: String? = null,
    val loading: Boolean = false
)