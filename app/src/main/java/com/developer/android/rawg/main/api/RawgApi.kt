package com.developer.android.rawg.main.api

import com.developer.android.rawg.main.api.model.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RawgApi {
    @GET("games")
    suspend fun getGames(
        @Query("key") key: String,
        @Query("genres") genres: String,
        @Query("page") page: Int
    ): GamesResponse
}