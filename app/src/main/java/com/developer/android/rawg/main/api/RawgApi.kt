package com.developer.android.rawg.main.api

import retrofit2.http.GET

interface RawgApi {
    @GET("games")
    suspend fun getGames():
}