package com.cabify.demo.ui

import com.cabify.demo.data.model.Product

sealed interface HomeUIState {
    /**
     * The feed is still loading.
     */
    object Loading : HomeUIState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data class Success(
        /**
         * The list of news resources contained in this feed.
         */
        val products: List<Product>,
    ) : HomeUIState

    //val productsT: List<Product>


}