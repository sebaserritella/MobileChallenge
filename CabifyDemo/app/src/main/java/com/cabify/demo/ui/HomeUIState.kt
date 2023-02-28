package com.cabify.demo.ui

import com.cabify.demo.data.model.Product

sealed interface HomeUIState {
    /**
     * The products is still loading.
     */
    object Loading : HomeUIState

    /**
     * The products is loaded.
     */
    data class Success(
        /**
         * The list of products.
         */
        val products: List<Product>,
    ) : HomeUIState

}