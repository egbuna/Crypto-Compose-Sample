package com.example.cryptocomposeapp.presentation.ui.coin

import com.example.cryptocomposeapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val success: CoinDetail? = null,
    val error: String? = null
)
