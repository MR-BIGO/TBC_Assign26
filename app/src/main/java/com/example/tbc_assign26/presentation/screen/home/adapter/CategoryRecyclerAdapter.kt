package com.example.tbc_assign26.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_assign26.databinding.CategoriesRvItemBinding
import com.example.tbc_assign26.presentation.model.CategoryPresentation

class CategoryRecyclerAdapter :
    ListAdapter<CategoryPresentation, CategoryRecyclerAdapter.CategoryViewHolder>(DiffCallback()) {

    inner class CategoryViewHolder(private val binding: CategoriesRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val category = currentList[adapterPosition]

            tvName.text = category.name

            val ballsAdapter = BallsRecyclerAdapter()
            rvBalls.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ballsAdapter
                ballsAdapter.setData(category.children)
            }
        }
    }

    fun setData(categories: List<CategoryPresentation>) {
        submitList(categories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoriesRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind()
    }

    private class DiffCallback : DiffUtil.ItemCallback<CategoryPresentation>() {
        override fun areItemsTheSame(
            oldItem: CategoryPresentation,
            newItem: CategoryPresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CategoryPresentation,
            newItem: CategoryPresentation
        ): Boolean {
            return oldItem == newItem
        }
    }
}