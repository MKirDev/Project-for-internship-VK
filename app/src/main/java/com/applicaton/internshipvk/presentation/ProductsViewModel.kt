package com.applicaton.internshipvk.presentation

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.applicaton.internshipvk.domain.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val productMapper: ProductMapper
) : ViewModel() {

    private val _state = MutableStateFlow<Parcelable?>(null)
    val state = _state.asStateFlow()

    val products = getProductsUseCase.execute().map {
        it.map { product ->
            productMapper.map(product)
        }
    }.cachedIn(viewModelScope)


    fun setStateRecycler(state: Parcelable?) {
        _state.value = state
    }

}