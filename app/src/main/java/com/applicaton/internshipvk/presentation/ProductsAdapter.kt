package com.applicaton.internshipvk.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.applicaton.internshipvk.databinding.ProductItemBinding
import com.bumptech.glide.Glide

class ProductsAdapter(
    private val onClick: (ProductModel) -> Unit
) : PagingDataAdapter<ProductModel, ProductViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("ProductsFragment", "заполнение данных")
        with(holder.binding) {
            title.text = item?.title
            rating.text = StringBuilder("Rating: ").append(item?.rating)
            price.text = item?.price.toString().plus(" $")
            item?.let {
                Log.d("ProductsFragment", "Images: ${it.images}")
                Glide
                    .with(photo.context)
                    .load(it.images[0])
                    .circleCrop()
                    .into(photo)
            }
        }
        holder.binding.product.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }

}

class DiffUtilCallback : DiffUtil.ItemCallback<ProductModel>() {
    override fun areItemsTheSame(
        oldItem: ProductModel,
        newItem: ProductModel
    ): Boolean = oldItem.id == newItem.id


    override fun areContentsTheSame(
        oldItem: ProductModel,
        newItem: ProductModel
    ): Boolean = oldItem == newItem

}

class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)