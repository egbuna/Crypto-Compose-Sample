package com.example.cryptocomposeapp.presentation.ui.coinlist

import com.example.cryptocomposeapp.domain.model.Coin

data class CoinListState(
    var isLoading: Boolean = false,
    val success: List<Coin> = emptyList(),
    val error: String? = null
)
