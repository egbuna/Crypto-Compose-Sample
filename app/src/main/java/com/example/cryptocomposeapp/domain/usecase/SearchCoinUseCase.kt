package com.example.cryptocomposeapp.domain.usecase

import com.example.cryptocomposeapp.domain.model.Coin
import javax.inject.Inject

class SearchCoinUseCase @Inject constructor() {

    operator fun invoke(coins: MutableList<Coin>, value: String): MutableList<Coin> {
        return coins.filter { it.name.contains(value, true) || it.symbol.contains(value, true) }.toMutableList()
    }
}