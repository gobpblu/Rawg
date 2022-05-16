package com.developer.android.rawg.main.ui.differenttypes.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.R
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.GameTypes

class DescriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
    private val textViewReleaseDate: TextView = itemView.findViewById(R.id.textViewItemReleaseDate)
    private val textViewPlaytime: TextView = itemView.findViewById(R.id.textViewItemPlaytime)
    private val textViewPlatforms: TextView = itemView.findViewById(R.id.textViewPlatforms)

    fun bind(game: GameTypes.DescriptionGame) {
        textViewName.text = game.name
        textViewReleaseDate.text = buildString { append(" ").append(game.released) }
        textViewPlaytime.text = buildString { append(" ").append(game.playTime).append(" hours") }
        textViewPlatforms.text = buildString {
            append("Platforms: ")
            game.parentPlatforms.forEachIndexed { index, platform ->
                append(platform.parentPlatform.name)
                if (index != game.parentPlatforms.lastIndex) append(", ")
            }
        }
    }
}