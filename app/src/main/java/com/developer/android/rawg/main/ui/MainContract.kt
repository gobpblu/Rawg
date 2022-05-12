package com.developer.android.rawg.main.ui

import com.developer.android.rawg.common.mvp.BaseFragmentContract
import com.developer.android.rawg.common.mvp.MvpPresenter
import com.developer.android.rawg.common.mvp.MvpView
import com.developer.android.rawg.main.api.model.GamesResponse
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.Games


interface MainContract : BaseFragmentContract {

    interface View : MvpView {
        fun showGames(games: Games, adapter: MainAdapter)
        fun showGameDetails(gameDetails: GameDetails)
    }

    interface Presenter : MvpPresenter<View> {
        fun getGames(adapter: MainAdapter, page: Int = 1, genres: String)
    }

}