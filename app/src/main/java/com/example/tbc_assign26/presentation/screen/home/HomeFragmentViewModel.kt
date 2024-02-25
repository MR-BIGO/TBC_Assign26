package com.example.tbc_assign26.presentation.screen.home

import android.util.Log
import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_assign26.data.common.Resource
import com.example.tbc_assign26.domain.use_case.FilterCategoriesUseCase
import com.example.tbc_assign26.domain.use_case.GetCategoriesUseCase
import com.example.tbc_assign26.presentation.event.HomeFragmentEvents
import com.example.tbc_assign26.presentation.mapper.fromDomain
import com.example.tbc_assign26.presentation.state.HomeFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getCategoriesCase: GetCategoriesUseCase,
    private val filterUseCase: FilterCategoriesUseCase
) :
    ViewModel() {

    private val _homeState = MutableStateFlow(HomeFragmentState())
    val homeState: SharedFlow<HomeFragmentState> = _homeState.asStateFlow()

    fun onEvent(event: HomeFragmentEvents) {
        when (event) {
            is HomeFragmentEvents.GetCategories -> {
                getCategories()
            }

            is HomeFragmentEvents.FilterCategories -> {
                d("did i make it here?", "i did")
                filterCategories(event.filter)
            }

            is HomeFragmentEvents.ResetError -> {
                setError(null)
            }
        }
    }

    private fun setError(error: String?) {
        viewModelScope.launch {
            _homeState.update { currentState -> currentState.copy(error = error) }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState -> currentState.copy(categories = it.data.map { category -> category.fromDomain() }) }
                    }

                    is Resource.Error -> {
                        setError(it.error)
                    }

                    is Resource.Loading -> {
                        _homeState.update { currentState -> currentState.copy(loading = it.loading) }
                    }
                }
            }
        }
    }

    private fun filterCategories(filter: String) {
        viewModelScope.launch {
            filterUseCase(filter).collect{
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState -> currentState.copy(categories = it.data.map { category -> category.fromDomain() }) }
                        Log.d("did i make it here?", "i did viewmodel filter")
                    }

                    is Resource.Error -> {
                        setError(it.error)
                    }

                    is Resource.Loading -> {
                        _homeState.update { currentState -> currentState.copy(loading = it.loading) }
                    }
                }
            }
        }
    }
}