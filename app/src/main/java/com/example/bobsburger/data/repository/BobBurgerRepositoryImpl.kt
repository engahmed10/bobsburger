package com.example.bobsburger.data.repository

import com.example.bobsburger.data.apiservice.ApiService
import com.example.bobsburger.data.response.BobBurgerResponse
import retrofit2.Response
import javax.inject.Inject

class BobBurgerRepositoryImpl @Inject constructor(val apiService: ApiService): BobBurgerRepository  {
    override suspend fun getCharacters(
    ): Response<List<BobBurgerResponse>> {
       return apiService.getCharacters()
    }

}