package com.example.ryanrileymtgfinal2.api

import com.example.ryanrileymtgfinal2.model.BoosterResponse
import com.example.ryanrileymtgfinal2.model.CardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Cards {

    @GET(SETS)
    suspend fun getBoosterList(): Response<BoosterResponse>

    @GET(SETS)
    suspend fun getCardList(
        @Path(BOOSTER) cardId: Int
    ): Response<CardResponse>

    companion object{
        const val BASE_URL = "https://api.magicthegathering.io/v1/"
        const val SETS = "sets"
        const val CODE = "ktk"
        const val BOOSTER = "$CODE/booster"
    }
}