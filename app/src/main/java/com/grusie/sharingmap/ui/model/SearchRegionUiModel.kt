package com.grusie.sharingmap.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchRegionUiModel(
    val address: String?,
    val categoryName: String?,
    val id: Long?,
    val placeName: String?,
    val latitude: Double?,
    val longitude: Double?
) : Parcelable