package com.developer.android.rawg.main.ui.differenttypes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.android.rawg.R
import com.developer.android.rawg.common.mvp.BaseFragmentMvp
import com.developer.android.rawg.databinding.FragmentGamesWithDifferentTypesBinding
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.model.Games
import com.developer.android.rawg.main.ui.differenttypes.adapter.DifferentTypesAdapter
import com.developer.android.rawg.main.ui.main.GameDetailsFragment
import org.koin.android.ext.android.inject

class GamesWithDifferentTypesFragment :
    BaseFragmentMvp<DifferentTypesContract.View, DifferentTypesContract.Presenter>(R.layout.fragment_games_with_different_types),
    DifferentTypesContract.View {

    private val adapterWithDifferentTypes: DifferentTypesAdapter by lazy {
        DifferentTypesAdapter(onClick = { showGameDetails(it) },
            getGames = { presenter.getGames(adapterWithDifferentTypes, it, "role-playing-games-rpg") })
    }

    override val presenter: DifferentTypesContract.Presenter by inject()

    private lateinit var binding: FragmentGamesWithDifferentTypesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGamesWithDifferentTypesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerViewWithDifferentTypes.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = adapterWithDifferentTypes
            }
            presenter.getGames(adapterWithDifferentTypes, genres = "role-playing-games-rpg")
        }
    }

    override fun showGames(games: Games, adapter: DifferentTypesAdapter) {
        adapter.addData(games)
    }

    override fun showGameDetails(gameDetails: GameTypes) {
        val fragment = GameDetailsFragment.newInstance(gameDetails)
        changeFragment(fragment, R.id.fragmentContainer)
    }

}