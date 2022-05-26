package com.developer.android.rawg.main.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.R
import com.developer.android.rawg.common.mvp.BaseFragmentMvp
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.databinding.FragmentAllGamesBinding
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.ui.main.adapter.Int
import com.developer.android.rawg.utils.GamesGenres
import org.koin.android.ext.android.inject
import timber.log.Timber

class AllGamesFragment :
    BaseFragmentMvp<MainContract.View, MainContract.Presenter>(R.layout.fragment_all_games),
    MainContract.View {

    override val presenter: MainContract.Presenter by inject()

    private val binding: FragmentAllGamesBinding by lazy {
        FragmentAllGamesBinding.inflate(layoutInflater)
    }

    private val adaptersList = mutableListOf<Int>()

    private val layoutManagersList = mutableListOf<LinearLayoutManager>()

    private val scrollListenerList = mutableListOf<MainScrollListener>()

    private val recyclersList: List<RecyclerView> by lazy {
        with(binding) {
            listOf(
                recyclerViewRpg,
                recyclerViewAdventure,
                recyclerViewSports
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        for (i in recyclersList.indices) {
            adaptersList.add(
                Int(
                    onGameItemClicked = { onGameItemClicked(it) },
                    onFailedListener = { onFailedListener() }
                ))
            layoutManagersList.add(
                LinearLayoutManager(requireContext(),
                    LinearLayoutManager.HORIZONTAL, false)
            )
        }

        for (i in recyclersList.indices) {
            scrollListenerList.add(
                MainScrollListener(layoutManager = layoutManagersList[i],
                    loadNextPage = {
                        presenter.getGames(
                            adapterIndex = i,
                            page = it,
                            genres = GamesGenres.values()[i].genre)
                    })
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("${getView()}")
        recyclersList.forEachIndexed { index, recycler ->
            recycler.apply {
                layoutManager = layoutManagersList[index]
                adapter = adaptersList[index]
                addOnScrollListener(scrollListenerList[index])
            }
        }

        swipeRefresh.setDistanceToTriggerSync(5)
        swipeRefresh.setProgressViewEndTarget(false, 50)
        swipeRefresh.setOnRefreshListener {
            for (index in adaptersList.indices) {
                adaptersList[index].clearItems()
                presenter.refresh(index, genres = GamesGenres.values()[index].genre)
            }
        }
        for (index in adaptersList.indices)
            presenter.getGames(adapterIndex = index, genres = GamesGenres.values()[index].genre)

    }

    override fun showGames(games: List<GameTypes?>, adapterIndex: kotlin.Int) {
        adaptersList[adapterIndex].setItems(games)
    }

    private fun onGameItemClicked(gameDetails: GameTypes.FullGame) {
        val fragment = GameDetailsFragment.newInstance(gameDetails)
        parentFragmentManager.findFragmentById(R.id.fragmentContainer)?.let {
            hideAndAddFragment(hideFragment = it,
                addFragment = fragment,
                id = R.id.fragmentContainer)
        }
    }

    private fun onFailedListener() {
        Toast.makeText(context, R.string.loading_error, Toast.LENGTH_SHORT)
            .show()
    }

    override fun showPagingState(adapterIndex: kotlin.Int, state: PagingState) {
        adaptersList[adapterIndex].setPagingState(state)
    }

    override fun showRefreshing(isRefreshing: Boolean) {
        binding.swipeRefresh.isRefreshing = isRefreshing
    }

    override fun showErrorMessage(e: Throwable) {
        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() = with(binding) {
        super.onDestroyView()
        for (i in 1..3) {
            recyclersList[i].removeOnScrollListener(scrollListenerList[i])
        }
    }

}