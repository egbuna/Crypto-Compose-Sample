package com.example.cryptocomposeapp.domain.repository

import com.example.cryptocomposeapp.data.remote.dto.CoinDetailDto
import com.example.cryptocomposeapp.data.remote.dto.CoinsDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinsDto>
    suspend fun getCoin(coinId: String): CoinDetailDto
}