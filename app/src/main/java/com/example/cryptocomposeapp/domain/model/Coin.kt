package com.example.cryptocomposeapp.domain.model

data class Coin(
    val id: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String,
    val is_active: Boolean,
)
