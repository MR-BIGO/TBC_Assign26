package com.example.tbc_assign26.presentation.event

sealed class HomeFragmentEvents {
    data object GetCategories : HomeFragmentEvents()
    data class FilterCategories(val filter: String): HomeFragmentEvents()
    data object ResetError: HomeFragmentEvents()
}