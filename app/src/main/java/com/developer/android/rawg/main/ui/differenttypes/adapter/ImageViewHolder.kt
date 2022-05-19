package com.developer.android.rawg.main.ui.differenttypes.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.GameTypes

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageViewIcon: ImageView = itemView.findViewById(R.id.imageViewIcon)


    fun bind(game: GameTypes.ImageGame) {
        Glide.with(itemView.context).load(game.backgroundImage).placeholder(R.drawable.abstract_game).into(imageViewIcon)
    }

}