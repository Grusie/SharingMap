package com.grusie.sharingmap.ui.main.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grusie.sharingmap.R
import com.grusie.sharingmap.data.MarkerItem
import com.grusie.sharingmap.designsystem.theme.Blue458FFF
import com.grusie.sharingmap.designsystem.theme.RedFF3D00
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.common.dpToPx
import com.grusie.sharingmap.ui.common.spToPx
import com.grusie.sharingmap.ui.model.MapBottomSheetExpendType
import com.grusie.sharingmap.ui.model.MarkerType
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.DisposableMapEffect
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationOverlay
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MapConstants
import ted.gun0912.clustering.clustering.Cluster
import ted.gun0912.clustering.naver.TedNaverClustering

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MapScreen(viewModel: MapViewModel = hiltViewModel()) {
    MapFeedModal(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        mapContent = { isFollowMode, expandedType, height, locationOnClicked ->
            MapMainView(
                isFollowMode = isFollowMode,
                bottomSheetExpandedType = expandedType,
                bottomSheetHeight = height,
                locationOnClicked = locationOnClicked
            )
        }
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapMainView(
    isFollowMode: Boolean,
    bottomSheetExpandedType: MapBottomSheetExpendType,
    bottomSheetHeight: Int,
    locationOnClicked: (Boolean) -> Unit
) {
    val locationTrackingMode =
        if (isFollowMode) LocationTrackingMode.Follow else LocationTrackingMode.NoFollow
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoom = 20.0,
                minZoom = 5.0,
                locationTrackingMode = locationTrackingMode,
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
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState()
    val isCompassEnabled = locationTrackingMode == LocationTrackingMode.Follow
    val locationSource = rememberFusedLocationSource(
        isCompassEnabled = isCompassEnabled,
    )
    val items: MutableList<MarkerItem> = remember {
        mutableListOf()
    }

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

    var currentPosition by remember {
        mutableStateOf(cameraPositionState.position.target)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        NaverMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties.copy(locationTrackingMode = locationTrackingMode),
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState,
            locationSource = locationSource,
            onOptionChange = {
                cameraPositionState.locationTrackingMode?.let {
                    locationOnClicked(it == LocationTrackingMode.Follow)
                }
            }
        ) {
            LocationOverlay(
                position = currentPosition,
                circleRadius = 0.dp,
                circleOutlineWidth = 0.dp,
                subIcon = null,
                icon = OverlayImage.fromResource(R.drawable.ic_location_mine),
            )

            var clusterManager by remember {
                mutableStateOf<TedNaverClustering<MarkerItem>?>(
                    null
                )
            }

            clusterManager?.addItems(items)

            DisposableMapEffect(items) { map ->
                if (clusterManager == null) {
                    clusterManager = TedNaverClustering
                        .with<MarkerItem>(context, map)
                        .customMarker { clusterItem ->
                            getCustomMarker(context, clusterItem)
                        }
                        .customCluster { clusterItems ->
                            getCustomCluster(context, clusterItems)
                        }
                        .clusterAddedListener { cluster, tedNaverMarker ->
                            when {
                                (cluster.size / 2) >= 5 -> {
                                    tedNaverMarker.marker.width =
                                        120.dp.value.toInt().dpToPx(context)
                                    tedNaverMarker.marker.height =
                                        120.dp.value.toInt().dpToPx(context)
                                }

                                (cluster.size / 2) >= 3 -> {
                                    tedNaverMarker.marker.width =
                                        100.dp.value.toInt().dpToPx(context)
                                    tedNaverMarker.marker.height =
                                        100.dp.value.toInt().dpToPx(context)
                                }

                                (cluster.size / 2) >= 2 -> {
                                    tedNaverMarker.marker.width =
                                        80.dp.value.toInt().dpToPx(context)
                                    tedNaverMarker.marker.height =
                                        80.dp.value.toInt().dpToPx(context)
                                }

                                (cluster.size / 2) >= 1 -> {
                                    tedNaverMarker.marker.width =
                                        60.dp.value.toInt().dpToPx(context)
                                    tedNaverMarker.marker.height =
                                        60.dp.value.toInt().dpToPx(context)
                                }
                            }
                        }
                        .make()
                }
                onDispose {
                    clusterManager?.clearItems()
                }
            }
        }

        if(bottomSheetExpandedType != MapBottomSheetExpendType.FULL) {
            CustomLocationButtonView(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = bottomSheetHeight.dp),

                onClick = {
                    locationOnClicked(true)
                }
            )
        }
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

fun getCustomCluster(context: Context, clusterItems: Cluster<MarkerItem>): View {

    return TextView(context).apply {
        text = if (clusterItems.size <= 10) "${clusterItems.size}" else "10+"

        setTextColor(Color.White.toArgb())
        textSize = 8.spToPx(context)

        gravity = Gravity.CENTER

        when {
            (clusterItems.size / 2) >= 5 -> {
                setPadding(
                    41.dp.value.toInt().dpToPx(context),
                    48.dp.value.toInt().dpToPx(context),
                    41.dp.value.toInt().dpToPx(context),
                    48.dp.value.toInt().dpToPx(context)
                )
            }

            (clusterItems.size / 2) >= 3 -> {
                setPadding(
                    44.dp.value.toInt().dpToPx(context),
                    40.dp.value.toInt().dpToPx(context),
                    44.dp.value.toInt().dpToPx(context),
                    40.dp.value.toInt().dpToPx(context)
                )
            }

            (clusterItems.size / 2) >= 2 -> {
                setPadding(
                    33.dp.value.toInt().dpToPx(context),
                    30.dp.value.toInt().dpToPx(context),
                    33.dp.value.toInt().dpToPx(context),
                    30.dp.value.toInt().dpToPx(context)
                )
            }

            (clusterItems.size / 2) >= 1 -> {
                setPadding(
                    19.dp.value.toInt().dpToPx(context),
                    24.dp.value.toInt().dpToPx(context),
                    19.dp.value.toInt().dpToPx(context),
                    24.dp.value.toInt().dpToPx(context)
                )
            }
        }

        background = ShapeDrawable(OvalShape()).apply {
            paint.color = Color.Black.toArgb()
        }
    }
}

fun getCustomMarker(context: Context, markerItem: MarkerItem): Marker {
    val (markerColor, markerSize) = when (markerItem.markerType) {
        MarkerType.Mine -> {
            Pair(RedFF3D00, 21.dpToPx(context))
        }

        MarkerType.Follower -> {
            Pair(Blue458FFF, 21.dpToPx(context))
        }
    }

    val bitmap = Bitmap.createBitmap(markerSize, markerSize, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val paint = Paint().apply {
        this.color = markerColor.toArgb()
        this.isAntiAlias = true
    }

    val radius = markerSize / 2f
    canvas.drawCircle(radius, radius, radius, paint)

    return Marker().apply {
        this.position = markerItem.position
        this.icon = OverlayImage.fromBitmap(bitmap)
        this.width = markerSize
        this.height = markerSize
    }
}

@Composable
@Preview
fun CustomLocationButtonPreview() {
    CustomLocationButtonView()
}