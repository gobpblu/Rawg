package com.developer.android.rawg.main.ui.differenttypes

import com.developer.android.rawg.common.mvp.BasePresenter
import com.developer.android.rawg.main.interactor.MainInteractor
import com.developer.android.rawg.main.ui.differenttypes.adapter.DifferentTypesAdapter
import com.developer.android.rawg.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class DifferentTypesPresenter(
    private val interactor: MainInteractor,
) : BasePresenter<DifferentTypesContract.View>(),
    DifferentTypesContract.Presenter {

    private val presenterScope = CoroutineScope(Dispatchers.Main.immediate)

    override fun getGames(adapter: DifferentTypesAdapter, page: Int, genres: String) {
        presenterScope.launch {
            try {
                val data = interactor.getResults(page, genres, Utils.TYPE_OF_VIEW_DIFFERENT_GAMES)
                view?.showGames(data, adapter)
            } catch (t: Throwable) {
                Timber.e(t.message)
            }
        }
    }
}