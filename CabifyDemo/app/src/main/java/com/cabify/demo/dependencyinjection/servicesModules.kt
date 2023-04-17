package com.cabify.demo.dependencyinjection

import com.cabify.demo.data.domain.service.ProductLocalDataSource
import com.cabify.demo.data.domain.service.ProductLocalDataSourceImpl
import com.cabify.demo.data.domain.service.ProductRemoteDataSource
import com.cabify.demo.data.domain.service.ProductRemoteDataSourceImpl
import org.koin.dsl.module

val servicesModules = module {
    single<ProductLocalDataSource> { ProductLocalDataSourceImpl() }
    single<ProductRemoteDataSource> { ProductRemoteDataSourceImpl() }
}