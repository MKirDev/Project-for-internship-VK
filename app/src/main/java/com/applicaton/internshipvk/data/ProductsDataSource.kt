package com.applicaton.internshipvk.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://dummyjson.com"

class ProductsDataSource {

    object RetrofitInstance {

        private val retrofit = Retrofit.Builder()
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productsApi: ProductsApi = retrofit.create(ProductsApi::class.java)
    }

    interface ProductsApi {
        @GET("products")
        suspend fun getListOfProducts(
            @Query("skip") skip: Int,
            @Query("limit") limit: Int = 20
        ): ProductListDto
    }

    fun getRetrofitInstance(): RetrofitInstance {
        return RetrofitInstance
    }
}

