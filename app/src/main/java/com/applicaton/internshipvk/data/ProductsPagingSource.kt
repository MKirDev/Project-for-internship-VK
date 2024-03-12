package com.applicaton.internshipvk.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.applicaton.internshipvk.domain.ProductsPagingSourceInterface
import com.applicaton.internshipvk.entity.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductsPagingSource(
    private val productsRepository: ProductsRepository
) : PagingSource<Int, Product>(), ProductsPagingSourceInterface {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 0
        return kotlin.runCatching {
            productsRepository.getProductList(page * 20)
        }.fold(
            onSuccess = {
                Log.d("PagingSourceLoad","$it в случае Success")
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                Log.d("PagingSourceLoad","$it в случае Error")
                LoadResult.Error(it)
            }
        )
    }

    override fun getProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = { ProductsPagingSource(productsRepository) }
        ).flow.also {
            Log.d("PagingSourceGetProducts","${it.map { 
                it
            }} в Pager")
        }
    }

    private companion object {
        private const val FIRST_PAGE = 0
    }

}