package com.cabify.demo.ui.cart

import androidx.compose.runtime.snapshots.SnapshotStateList

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
         * The quantity of products in the cart.
         */
        val cant: Int = 0, val shoppingCartItems: SnapshotStateList<ShoppingCartItem>
    ) : CartUIState
}