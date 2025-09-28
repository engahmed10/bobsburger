package com.example.bobsburger.data.apiservice

import com.example.bobsburger.data.response.BobBurgerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("characters/")
    suspend fun getCharacters(
        @Query("limit") limit: String = "20",
        @Query("skip") skip: String = "0"
    ): Response<List<BobBurgerResponse>>

}