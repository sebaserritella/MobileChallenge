package com.cabify.demo.data.domain.service

import com.cabify.demo.data.model.*
import java.math.BigDecimal
import java.util.*

class ProductLocalDataSourceImpl : ProductLocalDataSource {

    override fun getProducts(): ResponseApi {

        val response = ResponseApi(
            listOf(
                Voucher(
                    productId = UUID.randomUUID(),
                    code = ProductNames.VOUCHER.name,
                    name = "Cabify Voucher",
                    price = BigDecimal.valueOf(5)
                ),
                Product(
                    productId = UUID.randomUUID(),
                    code = ProductNames.MUG.name,
                    name = "Cabify Coffee Mug",
                    price = BigDecimal.valueOf(7.5)
                ),
                Tshirt(
                    productId = UUID.randomUUID(),
                    code = ProductNames.TSHIRT.name,
                    name = "Cabify T-shirt",
                    price = BigDecimal.valueOf(20)
                ),
            )
        )

        return response
    }
}