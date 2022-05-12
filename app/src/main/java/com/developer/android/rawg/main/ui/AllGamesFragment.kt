package com.developer.android.rawg.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.android.rawg.R
import com.developer.android.rawg.common.mvp.BaseFragmentMvp
import com.developer.android.rawg.databinding.FragmentAllGamesBinding
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.Games
import org.koin.android.ext.android.inject

class AllGamesFragment :
    BaseFragmentMvp<MainContract.View, MainContract.Presenter>(R.layout.fragment_all_games),
    MainContract.View {

    private val adapterRpg: MainAdapter by lazy {
        MainAdapter(onClick = { showGameDetails(it) },
            getGames = { presenter.getGames(adapterRpg, it, "role-playing-games-rpg") })
    }
    private val adapterAdventure: MainAdapter by lazy {
        MainAdapter(onClick = { showGameDetails(it) },
            getGames = { presenter.getGames(adapterAdventure, it, "adventure") })
    }
    private val adapterSports: MainAdapter by lazy {
        MainAdapter(onClick = { showGameDetails(it) },
            getGames = { presenter.getGames(adapterSports, it, "sports") })
    }

    override val presenter: MainContract.Presenter by inject()

    private lateinit var binding: FragmentAllGamesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAllGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerViewRpg.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = adapterRpg
            }
            recyclerViewAdventure.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = adapterAdventure
            }
            recyclerViewSports.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = adapterSports
            }
        }
        presenter.getGames(adapterRpg, genres = "role-playing-games-rpg")
        presenter.getGames(adapterAdventure, genres = "adventure")
        presenter.getGames(adapterSports, genres = "sports")
    }

    override fun showGames(games: Games, adapter: MainAdapter) {
        adapter.addData(games)
    }

    override fun showGameDetails(gameDetails: GameDetails) {
        val fragment = GameDetailsFragment.newInstance(gameDetails)
        changeFragment(fragment, R.id.fragment_container)
    }
}