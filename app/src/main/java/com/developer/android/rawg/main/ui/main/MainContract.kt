package com.developer.android.rawg.main.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.common.mvp.BaseFragmentContract
import com.developer.android.rawg.common.mvp.MvpPresenter
import com.developer.android.rawg.common.mvp.MvpView
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.model.Games
import com.developer.android.rawg.main.ui.main.adapter.MainAdapter


interface MainContract : BaseFragmentContract {

    interface View : MvpView {
        fun showGames(games: List<GameTypes?>, adapter: MainAdapter)
        fun showGameDetails(gameDetails: GameTypes.FullGame)
        fun showPagingState(state: PagingState)
    }

    interface Presenter : MvpPresenter<View> {
        fun getGames(adapter: MainAdapter, page: Int = 1, genres: String)
    }

}