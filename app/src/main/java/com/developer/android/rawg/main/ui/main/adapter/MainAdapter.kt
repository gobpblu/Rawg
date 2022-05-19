package com.developer.android.rawg.main.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.main.model.Games
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.model.ParentPlatformContainer
import com.developer.android.rawg.main.ui.differenttypes.adapter.DescriptionViewHolder
import com.developer.android.rawg.main.ui.differenttypes.adapter.FullViewHolder
import com.developer.android.rawg.main.ui.differenttypes.adapter.ImageViewHolder
import timber.log.Timber


class MainAdapter(
   private val onClick: (GameTypes.FullGame) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val gamesList = mutableListOf<GameTypes?>()
    private var pagingState: PagingState = PagingState.Idle
    private var next: String? = null
    private var page: Int = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when(viewType) {
            R.layout.game_item -> MainViewHolder(parent, onClick)
            R.layout.game_item_error -> ErrorViewHolder(parent)
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val game = gamesList[position]
        when (holder) {
            is MainViewHolder -> holder.bind(game as GameTypes.FullGame)
            is ErrorViewHolder -> holder.bind()
        }
        val anim: Animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.all_games_animation)
        holder.itemView.startAnimation(anim)
//        if (gamesList.size - position < 5 && next != null) getGames.invoke(++page)
    }

    override fun getItemViewType(position: Int): Int = when {
        position >= 0 -> R.layout.game_item
        else -> R.layout.game_item_error
    }

    override fun getItemCount() = gamesList.size

    fun addData(games: List<GameTypes?>) {
        Timber.i("$games")
        gamesList.addAll(games)
        notifyDataSetChanged()
    }

}