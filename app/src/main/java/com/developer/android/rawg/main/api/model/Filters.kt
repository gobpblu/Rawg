package com.developer.android.rawg.main.api.model

import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("years")
    val years: List<Year>
)
