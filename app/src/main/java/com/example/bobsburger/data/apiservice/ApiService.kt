package com.example.bobsburger.data.apiservice

import com.example.bobsburger.data.response.BobBurgerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//https://bobsburgers-api.herokuapp.com/characters/?limit=20&skip=0
    @GET("characters/")
    suspend fun getCharacters(
        @Query("limit") limit: String = "20",
        @Query("skip") skip: String = "0"
    ): Response<List<BobBurgerResponse>>

}