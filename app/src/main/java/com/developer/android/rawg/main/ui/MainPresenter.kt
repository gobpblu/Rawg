package com.developer.android.rawg.main.ui

import com.developer.android.rawg.common.mvp.BasePresenter
import com.developer.android.rawg.main.interactor.MainInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainPresenter(
    private val interactor: MainInteractor,
) : BasePresenter<MainContract.View>(),
    MainContract.Presenter {
    private val presenterScope = CoroutineScope(Dispatchers.Main.immediate)
    override fun getGames(
        adapter: MainAdapter, page: Int, genres: String,
    ) {
        presenterScope.launch {
            try {
                val data = interactor.getResults(page, genres)
                view?.showGames(data, adapter)
            } catch (t: Throwable) {
                Timber.e(t.message)
            }
        }
    }
}