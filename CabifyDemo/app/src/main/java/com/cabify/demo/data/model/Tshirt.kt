package com.cabify.demo.data.model

import java.math.BigDecimal
import java.util.*

data class Tshirt(
    override val productId: UUID? = null, //= UUID.randomUUID(),
    override val code: String = String(),
    override val name: String = String(),
    override val price: BigDecimal = BigDecimal.ZERO,
    override var quantity: Int = 1
) : Product(productId, code, name, price, quantity) {

    override fun discount(): Boolean {
        return super.quantity > 2
    }

    override fun amountUnit(): BigDecimal {
        return if (super.quantity > 2) {
            BigDecimal.valueOf(19.0)
        } else {
            super.price
        }
    }

    override fun amountTotal(): BigDecimal {
        return amountUnit().multiply(super.quantity.toBigDecimal())
    }
}