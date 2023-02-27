package com.cabify.demo.domain.service

import com.cabify.demo.data.model.ResponseApi

class ProductRemoteDataSourceImpl : ProductRemoteDataSource {

    override suspend fun getProducts(): ResponseApi {
        return ProductApiService.getInstance().getProducts()
    }
}