package com.grusie.sharingmap.ui.main.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Gray9A9C9F
import com.grusie.sharingmap.designsystem.theme.GrayF1F4F7
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.designsystem.theme.WhiteFBFBFB
import com.grusie.sharingmap.ui.main.map.CustomLocationButtonView
import com.grusie.sharingmap.ui.navigation.main.NavItem
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.util.MapConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(navController: NavController) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            var isFollowMode by remember { mutableStateOf(true) }
            val cameraPositionState = rememberCameraPositionState()
            val currentPosition by remember {
                derivedStateOf {
                    cameraPositionState.position
                }
            }
            var isShowEditPlaceBottomSheet by remember { mutableStateOf(false) }
            val editPlaceBottomSheetState =
                rememberModalBottomSheetState(skipPartiallyExpanded = true)

            val pickedAddress =
                "${currentPosition.target.latitude}, ${currentPosition.target.longitude}"

            SearchMapView(
                isFollowMode = isFollowMode,
                cameraPositionState = cameraPositionState,
            ) { isFollowMode = it }

            SearchTopView(
                paddingValues = paddingValues,
                onBackPressed = { navController.popBackStack() },
                goToSearchScreen = { navController.navigate(NavItem.SearchMap.screenRoute) }
            )


            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                CustomLocationButtonView(
                    modifier = Modifier
                        .align(Alignment.End),

                    onClick = {
                        isFollowMode = true
                    }
                )
                EditMapInfo(
                    currentLocation = pickedAddress,
                    showEditPlaceBottomSheet = { isShowEditPlaceBottomSheet = true }
                )

                if (isShowEditPlaceBottomSheet) {
                    EditPlaceBottomSheet(
                        address = pickedAddress,
                        sheetState = editPlaceBottomSheetState,
                        onDismiss = { isShowEditPlaceBottomSheet = false },
                        onSaveClick = {}
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun SearchMapView(
    isFollowMode: Boolean,
    cameraPositionState: CameraPositionState,
    setFollowMode: (Boolean) -> Unit,
) {
    val locationTrackingMode =
        if (isFollowMode) LocationTrackingMode.Follow else LocationTrackingMode.NoFollow

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoom = 20.0,
                minZoom = 5.0,
                extent = MapConstants.EXTENT_KOREA,
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
    val isCompassEnabled = locationTrackingMode == LocationTrackingMode.Follow

    val locationSource = rememberFusedLocationSource(
        isCompassEnabled = isCompassEnabled,
    )

    Box() {
        NaverMap(
            modifier = Modifier
                .fillMaxSize(),
            properties = mapProperties.copy(locationTrackingMode = locationTrackingMode),
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState,
            locationSource = locationSource,
            onOptionChange = {
                cameraPositionState.locationTrackingMode?.let {
                    setFollowMode(it == LocationTrackingMode.Follow)
                }
            }
        )

        Image(
            painterResource(com.naver.maps.map.R.drawable.navermap_default_marker_icon_blue),
            contentDescription = "Marker for Adding Location",
            modifier = Modifier
                .padding(bottom = 54.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun SearchTopView(
    paddingValues: PaddingValues = PaddingValues(),
    onBackPressed: () -> Unit = {},
    goToSearchScreen: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = { onBackPressed() }
        ) {
            Icon(
                modifier = Modifier.padding(),
                painter = painterResource(id = R.drawable.btn_back),
                contentDescription = null,
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    goToSearchScreen()
                }
                .align(Alignment.CenterVertically)
                .padding(start = 10.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = White)
                .padding(vertical = 14.5.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.edit_search_location),
            color = Gray9A9C9F,
        )
    }
}

@Composable
fun EditMapInfo(
    modifier: Modifier = Modifier,
    currentLocation: String = "",
    showEditPlaceBottomSheet: () -> Unit = {}
) {
    Column(
        modifier
            .background(
                brush = Brush.verticalGradient(
                    0.1f to Color.Transparent,
                    0.3f to White,
                    1f to White
                ),
                shape = RectangleShape,
                alpha = 1f
            )
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(top = 59.dp))
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(
                    color = GrayF1F4F7,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { }
                )
                .padding(vertical = 15.5.dp),
            color = Black,
            text = currentLocation
        )

        Spacer(modifier = Modifier.padding(bottom = 12.dp))

        Text(
            text = stringResource(id = R.string.edit_save_location),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(
                    color = Black,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    showEditPlaceBottomSheet()
                }
                .padding(22.5.dp),
            color = WhiteFBFBFB,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
@Preview(showBackground = true)
fun SearchTopViewPreView() {
    SearchTopView()
}

@Composable
@Preview(showBackground = true, backgroundColor = 0x00ff00)
fun EditMapInfoPreview() {
    EditMapInfo()
}