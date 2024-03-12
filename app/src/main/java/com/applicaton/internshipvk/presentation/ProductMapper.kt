package com.applicaton.internshipvk.presentation

import com.applicaton.internshipvk.entity.Product

class ProductMapper {
    fun map(product: Product): ProductModel {
        return ProductModel(
            id = product.id,
            title = product.title,
            description = product.description,
            price = product.price,
            discountPercentage = product.discountPercentage,
            rating = product.rating,
            stock = product.stock,
            brand = product.brand,
            category = product.category,
            thumbnail = product.thumbnail,
            images = product.images
        )
    }
}