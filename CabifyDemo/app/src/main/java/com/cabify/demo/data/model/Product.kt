package com.cabify.demo.data.model

import java.math.BigDecimal
import java.util.*

open class Product(
    open val productId: UUID? = null, //= UUID.randomUUID(),
    open val code: String = String(),
    open val name: String = String(),
    open val price: BigDecimal = BigDecimal.ZERO,
    open var quantity: Int = 1
) {

    open fun discount(): Boolean {
        return false
    }

    fun discountValue(): BigDecimal {
        return (price * quantity.toBigDecimal()).minus(amountTotal())
    }

    //This method contains the price and the discount pattern of each product
    open fun amountUnit(): BigDecimal {
        return price
    }

    open fun amountTotal(): BigDecimal {
        return price.multiply(quantity.toBigDecimal())
    }

    override fun equals(other: Any?): Boolean {
        if (other is Product) {
            if (this.code == other.code && this.productId == other.productId
                && this.name == other.name && this.price == other.price
                && this.quantity == other.quantity) return true
        }

        return false
    }
}

enum class ProductNames {
    TSHIRT, VOUCHER, MUG
}