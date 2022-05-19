package com.developer.android.rawg.main.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.android.rawg.R
import com.developer.android.rawg.common.mvp.BaseFragmentMvp
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.databinding.FragmentAllGamesBinding
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.ui.main.adapter.MainAdapter
import org.koin.android.ext.android.inject
import timber.log.Timber

class AllGamesFragment :
    BaseFragmentMvp<MainContract.View, MainContract.Presenter>(R.layout.fragment_all_games),
    MainContract.View {

    private val adapterRpg: MainAdapter by lazy {
        MainAdapter(onClick = { showGameDetails(it) })
    }
    private val adapterAdventure: MainAdapter by lazy {
        MainAdapter(onClick = { showGameDetails(it) })
    }
    private val adapterSports: MainAdapter by lazy {
        MainAdapter(onClick = { showGameDetails(it) })
    }

    override val presenter: MainContract.Presenter by inject()

    private lateinit var binding: FragmentAllGamesBinding

    private val linearLayoutManagerRpg by lazy {
        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private val linearLayoutManagerAdventure by lazy {
        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private val linearLayoutManagerSports by lazy {
        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private val scrollListenerRpg by lazy {
        MainScrollListener(layoutManager = linearLayoutManagerRpg,
            loadNextPage = {
                presenter.getGames(
                    adapter = adapterRpg, page = it, genres = "role-playing-games-rpg")
            })
    }

    private val scrollListenerAdventure by lazy {
        MainScrollListener(layoutManager = linearLayoutManagerAdventure,
            loadNextPage = {
                presenter.getGames(
                    adapter = adapterAdventure, page = it, genres = "adventure")
            })
    }

    private val scrollListenerSports by lazy {
        MainScrollListener(layoutManager = linearLayoutManagerSports,
            loadNextPage = {
                presenter.getGames(
                    adapter = adapterSports, page = it, genres = "sports")
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAllGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("$savedInstanceState")
        if (!recyclerViewRpg.isActivated) {
                recyclerViewRpg.apply {
                    layoutManager = linearLayoutManagerRpg
                    adapter = adapterRpg
                    addOnScrollListener(scrollListenerRpg)
                }
                recyclerViewAdventure.apply {
                    layoutManager = linearLayoutManagerAdventure
                    adapter = adapterAdventure
                    addOnScrollListener(scrollListenerAdventure)
                }
                recyclerViewSports.apply {
                    layoutManager = linearLayoutManagerSports
                    adapter = adapterSports
                    addOnScrollListener(scrollListenerSports)
                }
            presenter.getGames(adapterRpg, genres = "role-playing-games-rpg")
            presenter.getGames(adapterAdventure, genres = "adventure")
            presenter.getGames(adapterSports, genres = "sports")
        }

    }

    override fun showGames(games: List<GameTypes?>, adapter: MainAdapter) {
        adapter.addData(games)
    }

    override fun showGameDetails(gameDetails: GameTypes.FullGame) {
        val fragment = GameDetailsFragment.newInstance(gameDetails)
        changeFragment(fragment, R.id.fragmentContainer)
    }

    override fun showPagingState(state: PagingState) {

    }

    override fun onDestroyView() = with(binding) {
        super.onDestroyView()
        recyclerViewRpg.apply {
            layoutManager = null
            adapter = null
        }
        recyclerViewAdventure.apply {
            layoutManager = null
            adapter = null
        }
        recyclerViewSports.apply {
            layoutManager = null
            adapter = null
        }
        Timber.i("onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy()")
    }
}