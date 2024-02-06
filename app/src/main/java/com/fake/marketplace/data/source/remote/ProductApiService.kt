package com.fake.marketplace.data.source.remote

import com.fake.marketplace.data.source.remote.entities.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("/")
    suspend fun getProductList(): Response<ProductResponse>

}