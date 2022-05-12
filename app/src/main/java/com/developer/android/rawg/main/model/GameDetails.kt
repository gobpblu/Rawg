package com.developer.android.rawg.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameDetails(
    val id: Int,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    val backgroundImage: String?,
    val rating: Float,
    val ratingTop: Int,
    val ratings: List<Rating>,
    val ratingsCount: Int,
    val reviewsTextCount: Int,
    val added: Int,
    val adds: AddedByStatus,
    val metaCritic: Int,
    val playTime: Int,
    val updated: String,
    val genres: List<Genre>,
    val tags: List<Tag>,
    val shortScreenshots: List<ShortScreenshot>,
    val parentPlatforms: List<ParentPlatformContainer>
) : Parcelable
