package com.example.bobsburger.di

import com.example.bobsburger.data.apiservice.ApiService
import com.example.bobsburger.data.repository.BobBurgerRepository
import com.example.bobsburger.data.repository.BobBurgerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
         Retrofit.Builder()
            .baseUrl("https://bobsburgers-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideBobBurgerRepositoryImpl(apiService: ApiService): BobBurgerRepository =
        BobBurgerRepositoryImpl(apiService)

}