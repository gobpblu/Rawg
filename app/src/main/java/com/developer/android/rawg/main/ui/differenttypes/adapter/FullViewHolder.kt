package com.developer.android.rawg.main.ui.differenttypes.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.GameTypes
import com.developer.android.rawg.main.model.ParentPlatformContainer

class FullViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageViewIcon: ImageView = itemView.findViewById(R.id.imageViewIcon)
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
        Glide.with(itemView.context).load(game.backgroundImage).placeholder(R.drawable.abstract_game).into(imageViewIcon)
        textViewName.text = game.name
        textViewReleaseDate.text = buildString { append(" ").append(game.released) }
        textViewPlaytime.text = buildString { append(" ").append(game.playTime).append(" hours") }
        parentsBind(game.parentPlatforms)

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

    fun detach() = Glide.with(itemView).clear(imageViewIcon)
}