package com.grusie.sharingmap.data

import ted.gun0912.clustering.clustering.TedClusterItem
import ted.gun0912.clustering.geometry.TedLatLng

data class MarkerItem(
    val id: Long,
    val latitude: Double,
    val longitude: Double
) : TedClusterItem {
    override fun getTedLatLng(): TedLatLng {
        return TedLatLng(latitude, longitude)
    }
}