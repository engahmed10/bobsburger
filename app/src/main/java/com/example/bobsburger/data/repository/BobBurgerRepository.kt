package com.example.bobsburger.data.repository

import com.example.bobsburger.data.response.BobBurgerResponse
import retrofit2.Response

interface BobBurgerRepository {

    suspend fun getCharacters(): Response<List<BobBurgerResponse>>

}