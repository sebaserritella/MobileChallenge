package com.cabify.demo.domain.usecase

import com.cabify.demo.data.model.Product
import com.cabify.demo.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProductUseCase constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(): Flow<List<Product>> =
        productRepository.getProducts().map { response -> response.products }

}