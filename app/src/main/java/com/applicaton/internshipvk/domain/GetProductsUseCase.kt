package com.applicaton.internshipvk.domain

import androidx.paging.PagingData
import com.applicaton.internshipvk.entity.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetProductsUseCase(
   private val productsPagingSourceInterface: ProductsPagingSourceInterface
) {
    fun execute(): Flow<PagingData<Product>> {
      return productsPagingSourceInterface.getProducts()
    }
}