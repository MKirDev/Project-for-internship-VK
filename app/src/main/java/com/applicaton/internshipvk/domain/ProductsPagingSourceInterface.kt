package com.applicaton.internshipvk.domain

import androidx.paging.PagingData
import com.applicaton.internshipvk.entity.Product
import kotlinx.coroutines.flow.Flow

interface ProductsPagingSourceInterface {
    fun getProducts() : Flow<PagingData<Product>>
}