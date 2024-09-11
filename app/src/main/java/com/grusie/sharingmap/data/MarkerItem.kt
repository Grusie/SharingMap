package com.grusie.sharingmap.data

import com.grusie.sharingmap.ui.model.MarkerType
import com.naver.maps.geometry.LatLng
import ted.gun0912.clustering.clustering.TedClusterItem
import ted.gun0912.clustering.geometry.TedLatLng

data class MarkerItem(
    val id: Long,
    val position: LatLng,
    val markerType: MarkerType
) : TedClusterItem {
    override fun getTedLatLng(): TedLatLng {
        return TedLatLng(position.latitude, position.longitude)
    }
}