package com.applicaton.internshipvk.di

import com.applicaton.internshipvk.domain.GetProductsUseCase
import com.applicaton.internshipvk.presentation.ProductMapper
import com.applicaton.internshipvk.presentation.ProductsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideProductMapper(): ProductMapper {
        return ProductMapper()
    }

    @Provides
    fun provideProductsViewModelFactory(
        getProductsUseCase: GetProductsUseCase,
        productMapper: ProductMapper
    ) : ProductsViewModelFactory {
        return ProductsViewModelFactory(getProductsUseCase, productMapper)
    }
}