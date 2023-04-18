package com.cabify.demo.ui.cart

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabify.demo.data.model.Product
import com.cabify.demo.data.model.ProductNames
import kotlinx.coroutines.flow.*
import java.math.BigDecimal
import java.util.*

class ShoppingCartViewModel : ViewModel() {

    var shoppingCartItems = mutableStateListOf<ShoppingCartItem>()

    val cartUiState: MutableState<CartUIState> = mutableStateOf(CartUIState.Zero)

    val shoppingCartTotalPriceState: State<BigDecimal>
        get() = mutableStateOf(shoppingCartItems.sumOf { it.cartItemProductData.amountTotal() })

    val isCtaButtonEnabledState: State<Boolean>
        get() = handleShoppingCartState().run { mutableStateOf(!shoppingCartItems.isEmpty()) }

    private val onShoppingCartStateEvent =
        mutableStateOf<ShoppingCartStates>(ShoppingCartStates.Initial)

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    fun onOpenDialogClicked() {
        Log.d(this.toString(), "cashOut Button clicked")
        _showDialog.value = true
    }

    fun onDialogConfirm() {
        _showDialog.value = false
        shoppingCartItems = mutableStateListOf()
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun addItemToCart(code: String, name: String, price: BigDecimal) {
        shoppingCartItems.firstOrNull { x -> x.cartItemProductData.code == code }?.let {
            it.cartItemProductData.quantity += 1
        } ?: kotlin.run {
            shoppingCartItems.add(
                toShoppingCartItemViewModel(
                    UUID.randomUUID(), code, name, price, 1
                )
            )
        }

        cartUiState.value =
            CartUIState.Success(cant = getQuantity(), shoppingCartItems = shoppingCartItems)

        Log.d(this.toString(), "Item added: " + code + " Cart Size: " + shoppingCartItems.size)
        Log.d(this.toString(), "Price :" + shoppingCartTotalPriceState.value.toString())
    }

    private fun handleShoppingCartState() =
        snapshotFlow { onShoppingCartStateEvent.value }.onEach { shoppingCartStateEvent ->
            when (shoppingCartStateEvent) {
                is ShoppingCartStates.Initial -> {
                    // nothing to do
                }

                is ShoppingCartStates.DecrementProductItemFromShoppingCartEvent -> {
                    decrementItemToCart(shoppingCartStateEvent.code)
                }

                is ShoppingCartStates.IncrementProductItemFromShoppingCartEvent -> {
                    addItemToCart(shoppingCartStateEvent.code, "", BigDecimal.ZERO)
                }
            }
        }.launchIn(viewModelScope)

    private fun removeProductItemFromShoppingCart(code: String) {
        shoppingCartItems.firstOrNull { it.cartItemProductData.code == code }?.also {
            shoppingCartItems.remove(it)
        }
    }

    fun incrementItemToCart(code: String) {
        addItemToCart(code, "", BigDecimal.ZERO)
    }

    fun decrementItemToCart(code: String) {
        shoppingCartItems.firstOrNull { x -> x.cartItemProductData.code == code }?.let { item ->

                if (item.cartItemProductData.quantity == 1) {
                    removeProductItemFromShoppingCart(code)
                } else if (item.cartItemProductData.quantity > 1) {
                    item.cartItemProductData.quantity -= 1
                }
            }

        cartUiState.value = CartUIState.Success(
            cant = getQuantity(), shoppingCartItems = shoppingCartItems
        )
    }

    private fun shoppingCartItemsFakeRepository() = listOf(
        toShoppingCartItemViewModel(
            UUID.randomUUID(),
            ProductNames.VOUCHER.name,
            ProductNames.VOUCHER.name,
            BigDecimal.valueOf(5.0),
            3
        ), toShoppingCartItemViewModel(
            UUID.randomUUID(),
            ProductNames.TSHIRT.name,
            ProductNames.TSHIRT.name,
            BigDecimal.valueOf(20.0),
            6
        ), toShoppingCartItemViewModel(
            UUID.randomUUID(),
            ProductNames.MUG.name,
            ProductNames.MUG.name,
            BigDecimal.valueOf(7.50),
            2
        )
    )

    private fun toShoppingCartItemViewModel(
        productId: UUID, code: String, name: String, price: BigDecimal, quantity: Int
    ) = ShoppingCartItem(
        cartItemProductData = Product(
            productId = productId,
            code = code,
            name = name,
            price = price,
            quantity = quantity,
        ), onShoppingCartStateEvent = onShoppingCartStateEvent
    )

    private fun getQuantity(): Int {
        var cant = 0
        for (value in shoppingCartItems) {
            cant += value.cartItemProductData.quantity
        }
        return cant
    }
}