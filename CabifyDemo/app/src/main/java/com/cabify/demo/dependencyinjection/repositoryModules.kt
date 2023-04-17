package com.cabify.demo.dependencyinjection

import com.cabify.demo.data.domain.repository.ProductRepository
import com.cabify.demo.data.domain.repository.ProductRepositoryImpl
import org.koin.dsl.module

val repositoryModules = module {
    single<ProductRepository> { ProductRepositoryImpl() }
}