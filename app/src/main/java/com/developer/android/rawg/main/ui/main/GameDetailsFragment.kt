package com.developer.android.rawg.main.ui.main

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.common.mvp.BaseFragment
import com.developer.android.rawg.databinding.FragmentGameDetailsBinding
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.ui.main.adapter.ScreenshotsAdapter

private const val ARG1_GAME = "game"


class GameDetailsFragment : BaseFragment(R.layout.fragment_game_details) {
    private var gameDetails: GameTypes.FullGame? = null

    private lateinit var binding: FragmentGameDetailsBinding

    private val screenshotsAdapter: ScreenshotsAdapter by lazy {
        ScreenshotsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameDetails = it.getParcelable(ARG1_GAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        // image bind
        context?.let {
            Glide.with(it).load(gameDetails?.backgroundImage).into(imageViewDetailsIcon)
        }
        // Game Name bind
        textViewGameDetailsName.text = gameDetails?.name
        //MetaCritic bind
        gameDetails?.metaCritic?.let {
            textViewMetascoreValue.text = buildString { append("$it") }
            setMetascore(it)
        }
        recyclerViewScreenshots.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewScreenshots.adapter = screenshotsAdapter
        gameDetails?.let { game ->
            // Screenshots bind
            screenshotsAdapter.addData(game)
            // Release date bind
            textViewReleaseDateValue.text = game.released
            // Updated bind
            textViewUpdatedValue.text = game.updated.substring(0, 10)
            // ratingBar bind
            ratingBar.apply {
                rating = game.rating
            }
            // playtime bind
            textViewPlaytimeValue.text = buildString { append(game.playTime).append("h") }
            textViewPlatformValues.text = buildString {
                game.parentPlatforms.forEachIndexed { index, platform ->
                    append(platform.parentPlatform.name)
                    if (index != game.parentPlatforms.lastIndex) append(", ")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setMetascore(metaCritic: Int) = with(binding) {
        val metaCriticColor = when (metaCritic) {
            in 75..100 -> requireContext().getColor(R.color.metascore_green)
            in 50..74 -> requireContext().getColor(R.color.metascore_yellow)
            else -> requireContext().getColor(R.color.metascore_red)
        }
        textViewMetascoreValue.setTextColor(metaCriticColor)
        val drawable: GradientDrawable =
            textViewMetascoreValue.background as GradientDrawable
        drawable.setStroke(1, metaCriticColor)
    }

    override fun onDetach() {
        super.onDetach()
        view?.let { context?.let { it1 -> Glide.with(it1).clear(it) } }
    }

    companion object {
        @JvmStatic
        fun newInstance(game: GameTypes) =
            GameDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG1_GAME, game)
                }
            }
    }
}