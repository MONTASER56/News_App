package com.example.test23.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test23.databinding.LoadViewBinding

class LoadMoreAdapter(private val retry:()->Unit) : LoadStateAdapter<LoadMoreAdapter.ViewHolder>() {
    private lateinit var binding: LoadViewBinding



    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
       holder.setData(loadState)

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {

        binding = LoadViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(retry)
    }
    inner class ViewHolder(retry:()->Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener { retry() }
        }
        fun setData(state: LoadState) {
            binding.apply {

                Log.d("TAG", "setData: ")
                progressLoadMore.isVisible = state is LoadState.Loading
                textError.isVisible = state is LoadState.Loading
                btnRetry.isVisible = state is LoadState.Loading
            }
        }
    }
}