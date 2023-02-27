package com.cabify.demo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabify.demo.data.model.Product
import com.cabify.demo.domain.usecase.GetProductUseCase
import com.cabify.demo.ui.HomeUIState
import kotlinx.coroutines.flow.*

class HomeViewModel(
    getProductUseCase: GetProductUseCase
) : ViewModel() {

    val uiState: StateFlow<HomeUIState> = getProductUseCase()
        .map<List<Product>, HomeUIState>(HomeUIState::Success)
        .onStart { emit(HomeUIState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUIState.Loading
        )
}
