package com.applicaton.internshipvk.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.applicaton.internshipvk.databinding.LoadStateBinding

class CustomLoadStateAdapter : LoadStateAdapter<CustomLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: CustomLoadStateViewHolder, loadState: LoadState) = Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CustomLoadStateViewHolder {
        return CustomLoadStateViewHolder(
            LoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return super.displayLoadStateAsItem(loadState)
    }
}

class CustomLoadStateViewHolder(val binding: LoadStateBinding) :
    RecyclerView.ViewHolder(binding.root)