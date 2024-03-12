package com.applicaton.internshipvk.data

import com.applicaton.internshipvk.entity.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ProductsRepository(
    private val productsDataSource: ProductsDataSource
) {
    suspend fun getProductList(skip: Int): List<Product> = withContext(Dispatchers.IO) {
        delay(5000)
        productsDataSource.getRetrofitInstance().productsApi.getListOfProducts(skip).products
    }
}