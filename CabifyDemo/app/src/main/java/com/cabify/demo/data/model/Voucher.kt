package com.cabify.demo.data.model

import java.math.BigDecimal
import java.util.*

class Voucher(
    override val productId: UUID? = null, //= UUID.randomUUID(),
    override val code: String = String(),
    override val name: String = String(),
    override val price: BigDecimal = BigDecimal.ZERO,
    override var quantity: Int = 1
) : Product(productId, code, name, price, quantity) {

    override fun discount(): Boolean {
        return super.quantity > 1
    }

    override fun amountUnit(): BigDecimal {
        var amount = BigDecimal.ZERO

        amount += super.price.multiply(BigDecimal(super.quantity / 2))
        amount += if (super.quantity % 2 == 1) {
            super.price
        } else {
            BigDecimal.ZERO
        }

        return amount
    }

    override fun amountTotal(): BigDecimal {
        return amountUnit()
    }
}