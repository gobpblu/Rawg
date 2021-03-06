package com.developer.android.rawg.main.api.model

import com.google.gson.annotations.SerializedName

data class ParentPlatformResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)
