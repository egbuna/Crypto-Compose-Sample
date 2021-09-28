package com.example.cryptocomposeapp.data.remote.dto

import com.example.cryptocomposeapp.domain.model.Coin

data class CoinsDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinsDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        rank = rank,
        symbol = symbol,
        type = type,
        is_active
    )
}