package com.example.cryptocomposeapp.domain.usecase

import com.example.cryptocomposeapp.domain.model.Coin
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class SearchCoinUseCaseTest {

    private lateinit var searchCoinUseCase: SearchCoinUseCase
    private val coinsList = mutableListOf<Coin>()

    @Before
    fun setUp() {
        searchCoinUseCase = SearchCoinUseCase()
        populateFakeData()
    }

    @Test
    fun `Search for coin items, has search item`() {
        val searchWord = "1"
        val searchList = searchCoinUseCase(coinsList, searchWord)
        assertThat(searchList).hasSize(1)
    }

    @Test
    fun `Search for coin items, has no item`() {
        val searchWord = "6"
        val searchList = searchCoinUseCase(coinsList, searchWord)
        assertThat(searchList).hasSize(0)
    }

    private fun populateFakeData() {
        for (i in 0..5) {
            coinsList.add(
                Coin(
                    id = i.toString(),
                    name = "something$i",
                    rank = i,
                    type = i.toString(),
                    is_active = true,
                    symbol = i.toString()
                )
            )
        }
    }
}