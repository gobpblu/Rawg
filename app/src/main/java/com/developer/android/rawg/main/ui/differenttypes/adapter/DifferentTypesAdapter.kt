package com.developer.android.rawg.main.ui.differenttypes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.model.Games
import com.developer.android.rawg.utils.Utils.GAME_INFO_DESCRIPTION
import com.developer.android.rawg.utils.Utils.GAME_INFO_FULL
import com.developer.android.rawg.utils.Utils.GAME_INFO_IMAGE


class DifferentTypesAdapter(
    private val onClick: (GameTypes) -> Unit,
    private val getGames: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val gamesList = mutableListOf<GameTypes?>()
    private var next: String? = null
    private var page: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return when (viewType) {
            GAME_INFO_FULL -> FullViewHolder(itemView.inflate(R.layout.game_item_full, parent, false))
            GAME_INFO_IMAGE -> ImageViewHolder(itemView.inflate(R.layout.game_item_image, parent, false))
            GAME_INFO_DESCRIPTION -> DescriptionViewHolder(itemView.inflate(R.layout.game_item_description, parent, false))
            else -> throw IllegalStateException("Something went wrong")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val game = gamesList[position]
        when (holder) {
            is FullViewHolder -> holder.bind(game as GameTypes.FullGame)
            is ImageViewHolder -> holder.bind(game as GameTypes.ImageGame)
            is DescriptionViewHolder -> holder.bind(game as GameTypes.DescriptionGame)
        }
        holder.itemView.setOnClickListener { game?.let { it1 -> onClick.invoke(it1) } }
        val anim: Animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.all_games_animation)
        holder.itemView.startAnimation(anim)
        if (gamesList.size - position < 5 && next != null) getGames.invoke(++page)
    }

    override fun getItemCount() = gamesList.size

    override fun getItemViewType(position: Int): Int {
        return gamesList[position]?.type ?: 2
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when (holder) {
            is FullViewHolder -> Glide.with(holder.itemView).clear(holder.imageViewIcon)
            is ImageViewHolder -> Glide.with(holder.itemView).clear(holder.imageViewIcon)
        }
    }

    fun addData(games: Games) {
        gamesList.addAll(games.games)
        next = games.next
        notifyDataSetChanged()
    }
}