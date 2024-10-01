package com.grusie.sharingmap.ui.main.map

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomBottomSheet
import com.grusie.sharingmap.designsystem.component.Feed
import com.grusie.sharingmap.designsystem.component.UserLazyColumn
import com.grusie.sharingmap.designsystem.theme.Gray9A9C9F
import com.grusie.sharingmap.designsystem.theme.GrayE8EAEB
import com.grusie.sharingmap.designsystem.theme.Typography

@Composable
@ExperimentalMaterialApi
fun MapFeedModal(
    uiState: MapUiState,
    mapContent: @Composable (Boolean, Int, (Boolean) -> Unit) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    var expandedType by remember {
        mutableStateOf(ExpandedType.COLLAPSED)
    }
    val height by animateIntAsState(
        when (expandedType) {
            ExpandedType.HALF -> screenHeight / 7 * 3
            ExpandedType.FULL -> screenHeight
            ExpandedType.COLLAPSED -> 100
        }
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    var isFollowMode by remember { mutableStateOf(true) }

    Column {
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetElevation = 8.dp,
            sheetShape = RoundedCornerShape(20.dp),
            sheetContent = {
                var isUpdated = false
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(height.dp)
                ) {
                    MapBottomSheetDragHandle(modifier = Modifier.pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { change, dragAmount ->
                                change.consume()
                                if (!isUpdated) {
                                    expandedType = when {
                                        dragAmount < 0 && expandedType == ExpandedType.COLLAPSED -> {
                                            ExpandedType.HALF
                                        }

                                        dragAmount < 0 && expandedType == ExpandedType.HALF -> {
                                            ExpandedType.FULL
                                        }

                                        dragAmount > 0 && expandedType == ExpandedType.FULL -> {
                                            ExpandedType.HALF
                                        }

                                        dragAmount > 0 && expandedType == ExpandedType.HALF -> {
                                            ExpandedType.COLLAPSED
                                        }

                                        else -> {
                                            ExpandedType.FULL
                                        }
                                    }
                                    isUpdated = true
                                }
                            },
                            onDragEnd = {
                                isUpdated = false
                            }
                        )
                    })
                    MapFeedBottomSheet(uiState = uiState)
                }
            },
            sheetPeekHeight = height.dp
        ) {
            mapContent(isFollowMode, height) { isFollowMode = it }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapFeedBottomSheet(
    uiState: MapUiState,
) {
    var isArchivingBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    val archiveBottomSheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )

    if (isArchivingBottomSheetOpen) {
        CustomBottomSheet(
            title = stringResource(id = R.string.feed_bottom_sheet_archivings_title),
            isEmpty = uiState.selectedFeed?.archivings?.isEmpty() ?: true,
            emptyTitle = stringResource(id = R.string.feed_bottom_sheet_archivings_empty_title),
            content = {
                UserLazyColumn(
                    users = uiState.selectedFeed?.archivings ?: emptyList()
                )
            },
            sheetState = archiveBottomSheetState,
            onDismiss = { isArchivingBottomSheetOpen = false },
        )
    }

    if (uiState.feeds.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 70.dp)
        ) {
            items(uiState.feeds) {
                Feed(
                    feed = it,
                    isFollow = true,
                    onProfileClick = { /*TODO*/ },
                    onUserClick = { /*TODO*/ },
                    onImageClick = {},
                    onLocationClick = { /*TODO*/ },
                    onArchivingClick = {
                        isArchivingBottomSheetOpen = !isArchivingBottomSheetOpen
//                        viewModel.updateSelectedFeed(it)
                    },
                    onMeatBallClick = { /*isModalDialogOpen = !isModalDialogOpen*/ },
                    onLikeClick = { /*TODO*/ },
                    onChatClick = {
                        /*isCommentBottomSheetOpen = !isCommentBottomSheetOpen
                        viewModel.updateSelectedFeed(it)*/
                    },
                    onShareClick = { /*TODO*/ },
                )
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxHeight(0.4f)
        ) {
            Text(
                text = stringResource(id = R.string.feed_empty_text),
                textAlign = TextAlign.Center,
                style = Typography.headlineSmall,
                color = Gray9A9C9F,
                modifier =
                Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically),
            )
        }
    }
}

@Composable
fun MapBottomSheetDragHandle(modifier: Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        Spacer(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp)
                .align(Alignment.Center)
                .height(5.dp)
                .width(39.dp)
                .background(GrayE8EAEB)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MapFeedBottomSheetPreView() {
    MapFeedBottomSheet(MapUiState(feeds = emptyList()))
}


enum class ExpandedType {
    HALF, FULL, COLLAPSED
}