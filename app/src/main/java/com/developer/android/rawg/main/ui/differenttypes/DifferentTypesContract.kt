package com.developer.android.rawg.main.ui.differenttypes

import com.developer.android.rawg.common.mvp.BaseFragmentContract
import com.developer.android.rawg.common.mvp.MvpPresenter
import com.developer.android.rawg.common.mvp.MvpView
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.model.Games
import com.developer.android.rawg.main.ui.differenttypes.adapter.DifferentTypesAdapter

interface DifferentTypesContract : BaseFragmentContract {

    interface View : MvpView {
        fun showGames(games: Games, adapter: DifferentTypesAdapter)

        fun showGameDetails(gameDetails: GameTypes)
    }

    interface Presenter : MvpPresenter<View> {
        fun getGames(adapter: DifferentTypesAdapter, page: Int = 1, genres: String)
    }

}