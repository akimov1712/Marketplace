package com.fake.marketplace.di

import android.app.Application
import com.fake.marketplace.data.repositories.account.AccountRepositoryImpl
import com.fake.marketplace.data.repositories.product.ProductRepositoryImpl
import com.fake.marketplace.data.source.locale.database.AppDatabase
import com.fake.marketplace.domain.repository.account.AccountRepository
import com.fake.marketplace.domain.repository.product.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    companion object{

        @Provides
        @Singleton
        fun provideAccountDao(application: Application) =
            AppDatabase.getInstance(application).accountDao()

        @Provides
        @Singleton
        fun provideProductDao(application: Application) =
            AppDatabase.getInstance(application).productDao()

    }

}