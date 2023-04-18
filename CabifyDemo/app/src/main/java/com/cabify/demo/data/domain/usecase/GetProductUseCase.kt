package com.cabify.demo.data.domain.usecase

import com.cabify.demo.data.model.Product
import com.cabify.demo.data.domain.repository.ProductRepository
import com.cabify.demo.data.model.Tshirt
import com.cabify.demo.data.model.Voucher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProductUseCase constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return productRepository.getProducts().map { response ->
            response.products.map(fun(prod: Product): Product {
                return when (prod.code) {
                    "VOUCHER" -> Voucher(
                        prod.productId, prod.code, prod.name, prod.price, prod.quantity
                    )
                    "TSHIRT" -> Tshirt(
                        prod.productId, prod.code, prod.name, prod.price, prod.quantity
                    )
                    else -> prod
                }
            })
        }
    }
}