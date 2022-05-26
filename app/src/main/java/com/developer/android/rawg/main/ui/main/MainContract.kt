package com.developer.android.rawg.main.ui.main

import com.developer.android.rawg.common.mvp.BaseFragmentContract
import com.developer.android.rawg.common.mvp.MvpPresenter
import com.developer.android.rawg.common.mvp.MvpView
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.main.model.GameTypes


interface MainContract : BaseFragmentContract {

    interface View : MvpView {
        fun showGames(games: List<GameTypes?>, adapterIndex: kotlin.Int)
        fun showPagingState(adapterIndex: kotlin.Int, state: PagingState)
        fun showRefreshing(isRefreshing: Boolean)
        fun showErrorMessage(e: Throwable)
    }

    interface Presenter : MvpPresenter<View> {
        fun getGames(adapterIndex: kotlin.Int, page: kotlin.Int = 1, genres: String)
        fun refresh(adapterIndex: kotlin.Int, page: kotlin.Int = 1, genres: String)
    }

}