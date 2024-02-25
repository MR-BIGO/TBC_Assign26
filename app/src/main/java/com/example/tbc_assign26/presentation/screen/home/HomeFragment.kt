package com.example.tbc_assign26.presentation.screen.home

import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_assign26.databinding.FragmentHomeBinding
import com.example.tbc_assign26.presentation.event.HomeFragmentEvents
import com.example.tbc_assign26.presentation.screen.base.BaseFragment
import com.example.tbc_assign26.presentation.screen.home.adapter.CategoryRecyclerAdapter
import com.example.tbc_assign26.presentation.state.HomeFragmentState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoryRecyclerAdapter

    override fun setUp() {
        viewModel.onEvent(HomeFragmentEvents.GetCategories)
        setUpRv()
        bindObservers()
        listeners()
    }

    private fun setUpRv() {
        categoriesAdapter = CategoryRecyclerAdapter()
        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoriesAdapter
        }
    }

    private fun listeners() = with(binding) {
        var searchJob: Job? = null
        etCategory.doAfterTextChanged {et ->
            searchJob?.cancel()
            searchJob = viewLifecycleOwner.lifecycleScope.launch {
                delay(500)
                if (etCategory.text!!.isNotEmpty()) {
                    viewModel.onEvent(HomeFragmentEvents.FilterCategories(et.toString()))
                } else {
                    viewModel.onEvent(HomeFragmentEvents.GetCategories)
                }
            }
        }
    }

    private fun handleState(state: HomeFragmentState) {
        state.categories?.let {
            categoriesAdapter.setData(it)
        }

        state.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(HomeFragmentEvents.ResetError)
        }

        binding.progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE
    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleState(it)
                }
            }
        }
    }
}