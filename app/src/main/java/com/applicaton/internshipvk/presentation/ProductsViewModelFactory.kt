package com.applicaton.internshipvk.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.applicaton.internshipvk.domain.GetProductsUseCase

class ProductsViewModelFactory(
    private val getProductsUseCase: GetProductsUseCase,
    private val productMapper: ProductMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            return ProductsViewModel(getProductsUseCase, productMapper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}