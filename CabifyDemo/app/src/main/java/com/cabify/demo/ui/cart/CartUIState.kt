package com.cabify.demo.ui.cart

sealed interface CartUIState {
    /**
     * The products is still loading.
     */
    object Zero : CartUIState

    /**
     * The products is loaded.
     */
    data class Success(
        /**
         * The list of products.
         */
        //val products: List<ShoppingCartItem>,

        /**
         * The quantity of products in the cart.
         */
        val cant: Int = 0

    ) : CartUIState
}