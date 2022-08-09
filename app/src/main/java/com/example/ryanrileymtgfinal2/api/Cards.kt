package com.example.ryanrileymtgfinal2.api

import com.example.ryanrileymtgfinal2.model.BoosterDetailsResponse
import com.example.ryanrileymtgfinal2.model.BoosterNode
import com.example.ryanrileymtgfinal2.model.BoosterResponse
import com.example.ryanrileymtgfinal2.model.CardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Cards {

    @GET("sets")
    suspend fun getBoosterList(): Response<BoosterResponse>

    @GET("sets/{code}")
    suspend fun getBoosterDetails(
        @Path("code") code: String
    ): Response<BoosterDetailsResponse>

    @GET("sets/{code}/booster")
    suspend fun getCardList(
        @Path("code") code: String
    ): Response<CardResponse>

//    @GET("")

    companion object{
        const val BASE_URL = "https://api.magicthegathering.io/v1/"

    }
}