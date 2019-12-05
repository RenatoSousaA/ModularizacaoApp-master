package com.example.data.repository

import com.example.data.local.source.ProductCacheDataSource
import com.example.data.remote.source.ProductRemoteDataSource
import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import io.reactivex.Single

class ProductRepositoryImpl (
    private val productsCacheDataSource: ProductCacheDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource
): ProductRepository {
    override fun getProducts(forceUpdate: Boolean): Single<List<Product>> {
        return if (forceUpdate)
            getProductsRemote(forceUpdate)
        else
            productsCacheDataSource.getProducts()
                .flatMap { listJobs ->
                    when {
                        listJobs.isEmpty() -> getProductsRemote(false)
                        else -> Single.just(listJobs)
                    }
                }
    }


    private fun getProductsRemote(isUpdate: Boolean): Single<List<Product>> {
        return productRemoteDataSource.getProducts()
            .flatMap { listJobs ->
                if (isUpdate)
                    productsCacheDataSource.updateData(listJobs)
                else
                    productsCacheDataSource.insertData(listJobs)
                Single.just(listJobs)
            }
    }


}