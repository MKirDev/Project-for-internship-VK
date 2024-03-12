package com.applicaton.internshipvk.di

import com.applicaton.internshipvk.data.ProductsPagingSource
import com.applicaton.internshipvk.data.ProductsRepository
import com.applicaton.internshipvk.domain.GetProductsUseCase
import com.applicaton.internshipvk.domain.ProductsPagingSourceInterface
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideProductsPagingSourceInterface(
        productsPagingSource: ProductsPagingSource
    ): ProductsPagingSourceInterface {
        return productsPagingSource
    }

    @Provides
    fun provideGetProductsUseCase(
        productsPagingSourceInterface: ProductsPagingSourceInterface
    ) : GetProductsUseCase {
        return GetProductsUseCase(productsPagingSourceInterface)
    }
}