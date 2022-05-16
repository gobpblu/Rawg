package com.developer.android.rawg.main.repository

import com.developer.android.rawg.main.model.Games

interface MainRepository {
    suspend fun getGames(page: Int = 1, genres: String, typeOfView: Int): Games
}