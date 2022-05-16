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
import com.developer.android.rawg.main.model.Games
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.model.ParentPlatformContainer
import com.developer.android.rawg.main.ui.differenttypes.adapter.FullViewHolder
import timber.log.Timber


class MainAdapter(
   private val onClick: (GameTypes.FullGame) -> Unit,
   private val getGames: (Int) -> Unit
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val gamesList = mutableListOf<GameTypes?>()
    private var next: String? = null
    private var page: Int = 1


    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewIcon: ImageView = itemView.findViewById(R.id.imageViewIcon)
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewReleaseDate: TextView = itemView.findViewById(R.id.textViewItemReleaseDate)
        private val textViewPlaytime: TextView = itemView.findViewById(R.id.textViewItemPlaytime)
        private val platforms = listOf(
            itemView.findViewById<ImageView>(R.id.imageViewFirstIcon),
            itemView.findViewById(R.id.imageViewSecondIcon),
            itemView.findViewById(R.id.imageViewThirdIcon),
            itemView.findViewById(R.id.imageViewFourthIcon),
            itemView.findViewById(R.id.imageViewFifthIcon),
            itemView.findViewById(R.id.imageViewSixthIcon),
            itemView.findViewById(R.id.imageViewSeventhIcon),
            itemView.findViewById(R.id.imageViewEighthIcon),
            itemView.findViewById(R.id.imageViewNinthIcon),
            itemView.findViewById(R.id.imageViewTenthIcon))

        fun bind(game: GameTypes.FullGame) {
            Glide.with(itemView.context).load(game.backgroundImage).into(imageViewIcon)
            textViewName.text = game.name
            textViewReleaseDate.text = buildString { append(" ").append(game.released) }
            textViewPlaytime.text = buildString { append(" ").append(game.playTime).append(" hours") }
            parentsBind(game.parentPlatforms)
            itemView.setOnClickListener { onClick.invoke(game) }
        }

        private fun parentsBind(parentPlatforms: List<ParentPlatformContainer>) {
            var i = 0
            platforms.forEach { it.setImageDrawable(null) }
            parentPlatforms.forEach {
                when(it.parentPlatform.slug) {
                    "pc" -> platforms[i].setImageResource(R.drawable.pc_icon)
                    "playstation" -> platforms[i].setImageResource(R.drawable.playstation_icon)
                    "xbox" -> platforms[i].setImageResource(R.drawable.xbox_icon)
                    "ios" -> platforms[i].setImageResource(R.drawable.ios_icon)
                    "android" -> platforms[i].setImageResource(R.drawable.android_icon)
                    "mac" -> platforms[i].setImageResource(R.drawable.mac_icon)
                    "linux" -> platforms[i].setImageResource(R.drawable.linux_icon)
                    "nintendo" -> platforms[i].setImageResource(R.drawable.nintendo_icon)
                    "atari" -> platforms[i].setImageResource(R.drawable.atari_icon)
                    "commodore-amiga" -> platforms[i].setImageResource(R.drawable.commodore_amiga_icon)
                    "sega" -> platforms[i].setImageResource(R.drawable.sega_icon)
                    "3do" -> platforms[i].setImageResource(R.drawable.do_icon)
                    "neo-geo" -> platforms[i].setImageResource(R.drawable.neo_geo_icon)
                    "web" -> platforms[i].setImageResource(R.drawable.web_icon)
                }
                i++
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val game = gamesList[position]
        holder.bind(game as GameTypes.FullGame)
        val anim: Animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.all_games_animation)
        holder.itemView.startAnimation(anim)
        if (gamesList.size - position < 5 && next != null) getGames.invoke(++page)
    }

    override fun getItemCount() = gamesList.size

    fun addData(games: Games) {
        gamesList.addAll(games.games)
        next = games.next
        notifyDataSetChanged()
    }

}