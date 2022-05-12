package com.developer.android.rawg.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.android.rawg.R
import com.developer.android.rawg.main.model.GameDetails
import com.developer.android.rawg.main.model.ShortScreenshot

class ScreenshotsAdapter(): RecyclerView.Adapter<ScreenshotsAdapter.ScreenshotsViewHolder>() {
    private val screenshotsList = mutableListOf<ShortScreenshot>()

   inner class ScreenshotsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       private val imageViewScreenshot: ImageView = itemView.findViewById(R.id.imageViewScreenshot)

       fun bind(screenshot: ShortScreenshot) {
           Glide.with(itemView.context).load(screenshot.image).into(imageViewScreenshot)
       }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScreenshotsAdapter.ScreenshotsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.screenshot_item, parent, false)
        return ScreenshotsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScreenshotsAdapter.ScreenshotsViewHolder, position: Int) {
        val screenshot = screenshotsList[position]
        holder.bind(screenshot)
    }

    override fun getItemCount() = screenshotsList.size


    fun addData(game: GameDetails) {
        screenshotsList.addAll(game.shortScreenshots)
        notifyDataSetChanged()
    }
}