package com.grusie.sharingmap.ui.main.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.grusie.sharingmap.data.MarkerItem
import com.naver.maps.map.compose.DisposableMapEffect
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.util.MapConstants
import ted.gun0912.clustering.naver.TedNaverClustering

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen() {
    var mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoom = 10.0, minZoom = 5.0)
        )
    }
    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = false)
        )
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NaverMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties,
            uiSettings = mapUiSettings
        ) {
            val items = mutableListOf<MarkerItem>()

            val south = MapConstants.EXTENT_KOREA.southLatitude
            val west = MapConstants.EXTENT_KOREA.westLongitude
            val height = MapConstants.EXTENT_KOREA.northLatitude - south
            val width = MapConstants.EXTENT_KOREA.eastLongitude - west

            repeat(200) {
                items.add(
                    MarkerItem(
                        it.toLong(),
                        height * Math.random() + south,
                        width * Math.random() + west
                    )
                )
            }

            val context = LocalContext.current
            var clusterManager by remember { mutableStateOf<TedNaverClustering<MarkerItem>?>(null) }
            DisposableMapEffect(items) { map ->
                if (clusterManager == null) {
                    clusterManager = TedNaverClustering.with<MarkerItem>(context, map).make()
                }
                clusterManager?.addItems(items)
                onDispose {
                    clusterManager?.clearItems()
                }
            }
        }
    }
}