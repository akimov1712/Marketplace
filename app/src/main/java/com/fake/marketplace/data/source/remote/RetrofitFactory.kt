package com.fake.marketplace.data.source.remote

import com.fake.marketplace.Const.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private fun createLoggingInterceptor(): Interceptor{
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun createOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor())
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .baseUrl(BASE_URL)
        .build()

    val weatherApi = retrofit.create(ProductApiService::class.java)

}