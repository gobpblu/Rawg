package com.developer.android.rawg.main.repository

import com.developer.android.rawg.main.api.RawgApi
import com.developer.android.rawg.main.model.Games
import com.developer.android.rawg.main.model.MainConverter
import com.developer.android.rawg.utils.Utils
import com.developer.android.rawg.utils.Utils.API_KEY

class MainRemoteRepository(
    private val api: RawgApi
): MainRepository {
    override suspend fun getGames(page: Int, genres: String, typeOfView: Int): Games {
        val data = api.getGames(API_KEY, genres, page)
        return when(typeOfView) {
            Utils.TYPE_OF_VIEW_FULL_GAMES -> MainConverter.fromNetwork(data)
            Utils.TYPE_OF_VIEW_DIFFERENT_GAMES -> MainConverter.fromNetworkWithDifferentTypes(data)
            else -> { throw IllegalStateException("Not found such type of view") }
        }

    }


}