package com.applicaton.internshipvk.data

import com.applicaton.internshipvk.entity.ProductList

data class ProductListDto(
    override val products: List<ProductDto>
) : ProductList<ProductDto>
