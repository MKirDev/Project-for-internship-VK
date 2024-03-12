package com.applicaton.internshipvk.di

import com.applicaton.internshipvk.data.ProductsDataSource
import com.applicaton.internshipvk.data.ProductsPagingSource
import com.applicaton.internshipvk.data.ProductsRepository
import com.applicaton.internshipvk.domain.ProductsPagingSourceInterface
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideProductsDataSource() : ProductsDataSource {
        return ProductsDataSource()
    }

    @Provides
    fun provideProductsRepository(productsDataSource: ProductsDataSource): ProductsRepository {
        return ProductsRepository(productsDataSource)
    }

    @Provides
    fun provideProductsPagingSource(productsRepository: ProductsRepository): ProductsPagingSource {
        return ProductsPagingSource(productsRepository)
    }
}