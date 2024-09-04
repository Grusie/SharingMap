package com.grusie.sharingmap.ui.main.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.R
import com.grusie.sharingmap.data.MarkerItem
import com.grusie.sharingmap.designsystem.theme.Blue458FFF
import com.grusie.sharingmap.designsystem.theme.RedFF3D00
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.model.MarkerType
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.DisposableMapEffect
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MapConstants
import ted.gun0912.clustering.naver.TedNaverClustering

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen() {
    var isFollowMode by remember { mutableStateOf(true) }
    val locationTrackingMode =
        if (isFollowMode) LocationTrackingMode.Follow else LocationTrackingMode.NoFollow
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoom = 20.0,
                minZoom = 5.0,
                locationTrackingMode = locationTrackingMode
            ),
        )
    }

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                isZoomControlEnabled = false,
                isTiltGesturesEnabled = false,
                isRotateGesturesEnabled = false
            )
        )
    }

    val cameraPositionState = rememberCameraPositionState()

    val isCompassEnabled = locationTrackingMode == LocationTrackingMode.Follow

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NaverMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties.copy(locationTrackingMode = locationTrackingMode),
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState,
            locationSource = rememberFusedLocationSource(
                isCompassEnabled = isCompassEnabled,
            ),
            onOptionChange = {
                cameraPositionState.locationTrackingMode?.let {
                    isFollowMode = it == LocationTrackingMode.Follow
                }
            }
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
                        LatLng(height * Math.random() + south, width * Math.random() + west),
                        if (it % 2 == 0) MarkerType.Mine else MarkerType.Follower
                    )
                )
            }

            val context = LocalContext.current
            var clusterManager by remember { mutableStateOf<TedNaverClustering<MarkerItem>?>(null) }
            DisposableMapEffect(items) { map ->
                if (clusterManager == null) {
                    clusterManager = TedNaverClustering
                        .with<MarkerItem>(context, map)
                        .customMarker { clusterItem ->
                            createCustomMarker(context, clusterItem)
                        }
                        .make()
                }
                clusterManager?.addItems(items)
                onDispose {
                    clusterManager?.clearItems()
                }
            }
        }

        CustomLocationButtonView(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                isFollowMode = true
            }
        )
    }
}

@Composable
fun CustomLocationButtonView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(40.dp)
            .padding(end = 8.dp, bottom = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = White),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(6.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_my_location),
            contentDescription = "my location button"
        )
    }
}

fun createCustomMarker(context: Context, markerItem: MarkerItem): Marker {
    // dp를 px로 변환

    val (markerColor, markerSize) = when (markerItem.markerType) {
        MarkerType.Mine -> {
            Pair(RedFF3D00, 21.dp)
        }

        MarkerType.Follower -> {
            Pair(Blue458FFF, 21.dp)
        }
    }

    val density = context.resources.displayMetrics.density
    val diameterPx = (markerSize.value * density).toInt()

    val bitmap = Bitmap.createBitmap(diameterPx, diameterPx, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val paint = Paint().apply {
        this.color = markerColor.toArgb()
        this.isAntiAlias = true
    }

    val radius = diameterPx / 2f
    canvas.drawCircle(radius, radius, radius, paint)

    return Marker().apply {
        this.position = markerItem.position
        this.icon = OverlayImage.fromBitmap(bitmap)
        this.width = diameterPx
        this.height = diameterPx
    }
}

@Composable
@Preview
fun CustomLocationButtonPreview() {
    CustomLocationButtonView()
}