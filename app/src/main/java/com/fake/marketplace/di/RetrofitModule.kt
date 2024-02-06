package com.fake.marketplace.di

import com.fake.marketplace.data.source.remote.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RetrofitModule {
    companion object{

        @Provides
        @Singleton
        fun provideProductApi() = RetrofitFactory.weatherApi

    }
}