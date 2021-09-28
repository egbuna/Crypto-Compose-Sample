package com.example.cryptocomposeapp.di

import com.example.cryptocomposeapp.common.Constant.BASE_URL
import com.example.cryptocomposeapp.data.remote.CoinPaparikaApi
import com.example.cryptocomposeapp.data.repository.CoinRepositoryImpl
import com.example.cryptocomposeapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCaprikaApi(): CoinPaparikaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaparikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaparikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

}