package com.developer.android.rawg.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class GameTypes : Parcelable {
    abstract val type: Int
    @Parcelize
    data class FullGame(
        val name: String,
        val released: String?,
        val backgroundImage: String?,
        val rating: Float,
        val metaCritic: Int,
        val playTime: Int,
        val updated: String,
        val shortScreenshots: List<ShortScreenshot>,
        val parentPlatforms: List<ParentPlatformContainer>,
        override val type: Int = 2
    ) : GameTypes()

    @Parcelize
    data class ImageGame(
        val backgroundImage: String?,
        override val type: Int = 3
    ) : GameTypes()

    @Parcelize
    data class DescriptionGame(
        val name: String,
        val released: String,
        val playTime: Int,
        val parentPlatforms: List<ParentPlatformContainer>,
        override val type: Int = 4
    ) : GameTypes()
}
