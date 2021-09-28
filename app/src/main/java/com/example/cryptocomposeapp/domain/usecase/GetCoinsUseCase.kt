package com.example.cryptocomposeapp.domain.usecase

import android.util.Log
import com.example.cryptocomposeapp.common.Resource
import com.example.cryptocomposeapp.data.remote.dto.toCoin
import com.example.cryptocomposeapp.domain.model.Coin
import com.example.cryptocomposeapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = coinRepository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(message =  e.localizedMessage ?: "Omo something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>(message = "Couldn't complete the request, Please make sure you're connected to the internet"))
        }
    }
}