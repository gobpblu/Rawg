package com.developer.android.rawg.main.ui.main

import com.developer.android.rawg.common.mvp.BasePresenter
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.main.interactor.MainInteractor
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.utils.Utils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import timber.log.Timber

class MainPresenter(
    private val interactor: MainInteractor,
) : BasePresenter<MainContract.View>(),
    MainContract.Presenter {
    private var coroutineScope = CoroutineScope(Dispatchers.Main.immediate)
    private var games = mutableListOf<GameTypes?>()
    private var paginationEnded = false


    override fun getGames(adapterIndex: Int, page: Int, genres: String) {
        if (paginationEnded) return

        coroutineScope.launch {
            try {
                view?.showPagingState(adapterIndex, PagingState.Loading)
                val data = interactor.getResults(page, genres, Utils.TYPE_OF_VIEW_FULL_GAMES)
                if (data.count == 0) {
                    paginationEnded = true
                } else {
                    games.addAll(data.games)
                    view?.showGames(data.games, adapterIndex)
                }
            } catch (e: CancellationException) {
                Timber.e("Cancelled loading request")
            } catch (t: Throwable) {
                Timber.e(t.message)
                view?.showPagingState(adapterIndex, PagingState.Error(t))
            }
        }
    }

    override fun refresh(adapterIndex: Int, page: kotlin.Int, genres: String) {
        paginationEnded = false

        coroutineScope.launch {
            view?.showRefreshing(true)
            getGames(adapterIndex, page, genres)
            view?.showRefreshing(false)
        }
    }
}