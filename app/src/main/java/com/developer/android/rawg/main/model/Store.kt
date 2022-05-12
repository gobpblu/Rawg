package com.developer.android.rawg.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Store(
    val id: Int,
    val storeInfo: StoreInfo
) : Parcelable
