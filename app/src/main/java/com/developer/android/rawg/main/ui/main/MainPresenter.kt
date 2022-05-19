package com.developer.android.rawg.main.ui.main

import com.developer.android.rawg.common.mvp.BasePresenter
import com.developer.android.rawg.main.interactor.MainInteractor
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.ui.main.adapter.MainAdapter
import com.developer.android.rawg.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainPresenter(
    private val interactor: MainInteractor,
) : BasePresenter<MainContract.View>(),
    MainContract.Presenter {
    private var coroutineScope = CoroutineScope(Dispatchers.Main.immediate)
    private var games = mutableListOf<GameTypes?>()
    private var paginationEnded = false


    override fun getGames(adapter: MainAdapter, page: Int, genres: String) {
        coroutineScope.launch {
            try {
                val data = interactor.getResults(page, genres, Utils.TYPE_OF_VIEW_FULL_GAMES)
                view?.showGames(data.games, adapter)
            } catch (t: Throwable) {
                Timber.e(t.message)
            }
        }
    }
}