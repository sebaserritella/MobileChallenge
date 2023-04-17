package com.cabify.demo.data.domain.repository

import com.cabify.demo.data.model.ResponseApi
import com.cabify.demo.data.domain.service.ProductLocalDataSource
import com.cabify.demo.data.domain.service.ProductLocalDataSourceImpl
import com.cabify.demo.data.domain.service.ProductRemoteDataSource
import com.cabify.demo.data.domain.service.ProductRemoteDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl : ProductRepository {
    private val remoteDataSource: ProductRemoteDataSource = ProductRemoteDataSourceImpl()
    private val localDataSource: ProductLocalDataSource = ProductLocalDataSourceImpl()

    override fun getProducts(): Flow<ResponseApi> = flow {
        val latestProducts = remoteDataSource.getProducts()
        emit(latestProducts)
    }

    override fun getLocalProducts(): ResponseApi = localDataSource.getProducts()

}