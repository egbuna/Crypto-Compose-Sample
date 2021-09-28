package com.example.cryptocomposeapp.domain.usecase

import com.example.cryptocomposeapp.common.Resource
import com.example.cryptocomposeapp.data.remote.dto.toCoinDetail
import com.example.cryptocomposeapp.domain.model.CoinDetail
import com.example.cryptocomposeapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = coinRepository.getCoin(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(message =  e.localizedMessage ?: "Omo something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>(message = "Couldn't complete the request, Please make sure you're connected to the internet"))
        }
    }
}