package com.cabify.demo.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.layout.DisplayFeature
import com.cabify.demo.data.model.Product
import com.cabify.demo.ui.HomeUIState
import com.cabify.demo.ui.cart.ShoppingCartViewModel
import com.cabify.demo.ui.components.CabifyProductListItem
import com.cabify.demo.ui.components.DetailAppBar
import com.cabify.demo.ui.components.SearchBar
import com.cabify.demo.ui.utils.ContentType
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ProductsScreen(
    contentType: ContentType,
    displayFeatures: List<DisplayFeature>,
    modifier: Modifier = Modifier,
    shoppingCartViewModel: ShoppingCartViewModel
) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val homeUIState by homeViewModel.uiState.collectAsStateWithLifecycle()

    val productLazyListState = rememberLazyListState()

    when (homeUIState) {
        HomeUIState.Loading -> {}
        is HomeUIState.Success -> {
            if (contentType == ContentType.DUAL_PANE) {
                TwoPane(
                    first = {
                        CabifyProductList(
                            lazyListState = productLazyListState,
                            shoppingCartViewModel = shoppingCartViewModel,
                            homeUIState = homeUIState
                        )
                    },
                    second = {
                        CabifyProductDetail(
                            item = ((homeUIState as HomeUIState.Success).products.first()), isFullScreen = false
                        )
                    },
                    strategy = HorizontalTwoPaneStrategy(splitFraction = 0.5f, gapWidth = 16.dp),
                    displayFeatures = displayFeatures
                )
            } else {
                Box(modifier = modifier.fillMaxSize()) {
                    SinglePaneContent(
                        productLazyListState = productLazyListState,
                        modifier = Modifier.fillMaxSize(),
                        shoppingCartViewModel = shoppingCartViewModel
                    )
                }
            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SinglePaneContent(
    productLazyListState: LazyListState,
    modifier: Modifier = Modifier,
    shoppingCartViewModel: ShoppingCartViewModel
) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val homeUIState by homeViewModel.uiState.collectAsStateWithLifecycle()

    CabifyProductList(
        lazyListState = productLazyListState,
        modifier = modifier,
        shoppingCartViewModel = shoppingCartViewModel,
        homeUIState = homeUIState
    )
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CabifyProductList(
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
    shoppingCartViewModel: ShoppingCartViewModel,
    homeUIState: HomeUIState
) {
    LazyColumn(modifier = modifier, state = lazyListState) {
        item {
            SearchBar(modifier = Modifier.fillMaxWidth())
        }
        when (homeUIState) {
            HomeUIState.Loading -> {
                item {
                    CabifyProductListItem(
                        productItem = Product(),
                        shoppingCartViewModel = shoppingCartViewModel
                    )
                }
                item {
                    CabifyProductListItem(
                        productItem = Product(),
                        shoppingCartViewModel = shoppingCartViewModel
                    )
                }
            }
            is HomeUIState.Success -> {
                items(items = homeUIState.products, key = { it.code }) { product ->
                    CabifyProductListItem(
                        productItem = product,
                        shoppingCartViewModel = shoppingCartViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun CabifyProductDetail(
    item: Product,
    isFullScreen: Boolean = true,
    modifier: Modifier = Modifier.fillMaxSize(),
    onBackPressed: () -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(top = 16.dp)
    ) {
        item {
            DetailAppBar(item, isFullScreen) {
                onBackPressed()
            }
        }
    }
}
