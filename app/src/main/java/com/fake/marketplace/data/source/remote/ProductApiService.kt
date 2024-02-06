package com.fake.marketplace.data.source.remote

import com.fake.marketplace.data.source.remote.entities.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("v3/97e721a7-0a66-4cae-b445-83cc0bcf9010/")
    suspend fun getProductList(): Response<ProductResponse>

}