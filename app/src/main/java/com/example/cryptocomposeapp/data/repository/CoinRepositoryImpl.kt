package com.example.cryptocomposeapp.data.repository

import com.example.cryptocomposeapp.data.remote.CoinPaparikaApi
import com.example.cryptocomposeapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinPaparikaApi: CoinPaparikaApi
) : CoinRepository {

    override suspend fun getCoins() = coinPaparikaApi.getCoins()

    override suspend fun getCoin(coinId: String) = coinPaparikaApi.getCoin(coinId)
}