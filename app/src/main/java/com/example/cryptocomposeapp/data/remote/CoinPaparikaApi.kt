package com.example.cryptocomposeapp.data.remote

import com.example.cryptocomposeapp.data.remote.dto.CoinDetailDto
import com.example.cryptocomposeapp.data.remote.dto.CoinsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaparikaApi {

    @GET("v1/coins")
    suspend fun getCoins(): List<CoinsDto>

    @GET("v1/coins/{coin_id}")
    suspend fun getCoin(@Path("coin_id") coinId: String): CoinDetailDto
}