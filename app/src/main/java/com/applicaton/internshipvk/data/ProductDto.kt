package com.applicaton.internshipvk.data

import com.applicaton.internshipvk.entity.Product

data class ProductDto(
    override val id: Int,
    override val title: String,
    override val description: String,
    override val price: Int,
    override val discountPercentage: Double,
    override val rating: Double,
    override val stock: Int,
    override val brand: String,
    override val category: String,
    override val thumbnail: String,
    override val images: List<String>
) : Product