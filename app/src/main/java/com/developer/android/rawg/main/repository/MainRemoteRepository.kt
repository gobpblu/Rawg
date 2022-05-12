package com.developer.android.rawg.main.repository

import com.developer.android.rawg.main.api.RawgApi
import com.developer.android.rawg.main.model.Games
import com.developer.android.rawg.main.model.MainConverter
import com.developer.android.rawg.utils.Utils.API_KEY
import timber.log.Timber

class MainRemoteRepository(
    private val api: RawgApi
): MainRepository {
    override suspend fun getGames(page: Int, genres: String): Games {
        val data = api.getGames(API_KEY, genres, page)
        return MainConverter.fromNetwork(data)
    }


}