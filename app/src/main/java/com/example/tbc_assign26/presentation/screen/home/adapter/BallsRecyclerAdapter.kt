package com.example.tbc_assign26.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_assign26.databinding.BallsRvItemBinding

class BallsRecyclerAdapter : RecyclerView.Adapter<BallsRecyclerAdapter.BallsViewHolder>() {

    private var balls: Int = 0

    inner class BallsViewHolder(private val binding: BallsRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setData(balls: Int) {
        this.balls = balls

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BallsViewHolder {
        return BallsViewHolder(
            BallsRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = if (balls < 4) balls else 4

    override fun onBindViewHolder(holder: BallsViewHolder, position: Int) {}
}