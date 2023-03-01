package com.cabify.demo.ui.cart

sealed class ShoppingCartStates {
    object Initial : ShoppingCartStates()
    data class IncrementProductItemFromShoppingCartEvent(val code: String) : ShoppingCartStates()
    data class DecrementProductItemFromShoppingCartEvent(val code: String) : ShoppingCartStates()
}