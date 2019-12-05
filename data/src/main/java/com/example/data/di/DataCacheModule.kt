package com.example.data.di

import com.example.data.local.database.ProductDataBase
import com.example.data.local.source.ProductCacheDataSource
import com.example.data.local.source.ProductCacheDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModule = module {
    single { ProductDataBase.createDataBase(androidContext()) }
    factory<ProductCacheDataSource> { ProductCacheDataSourceImpl(productDao = get())  }
}