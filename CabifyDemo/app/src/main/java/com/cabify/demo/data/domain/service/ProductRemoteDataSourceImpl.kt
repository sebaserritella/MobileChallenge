package com.cabify.demo.data.domain.service

import com.cabify.demo.data.model.ResponseApi

class ProductRemoteDataSourceImpl : ProductRemoteDataSource {

    override suspend fun getProducts(): ResponseApi {
        return ProductApiService.getInstance().getProducts()
    }
}