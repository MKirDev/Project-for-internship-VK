package com.applicaton.internshipvk.di

import com.applicaton.internshipvk.presentation.ProductsViewModelFactory
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
interface AppComponent {
    fun productsViewModelFactory(): ProductsViewModelFactory
}