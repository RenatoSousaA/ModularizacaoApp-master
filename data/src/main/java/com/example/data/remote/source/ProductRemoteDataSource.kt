package com.example.data.remote.source

import com.example.domain.entity.Product
import io.reactivex.Single

interface ProductRemoteDataSource {

    fun getProducts() : Single<List<Product>>

}