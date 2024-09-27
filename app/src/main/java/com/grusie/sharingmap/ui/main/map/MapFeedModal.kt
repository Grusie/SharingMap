package com.grusie.sharingmap.ui.main.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.data.fakeFeeds
import com.grusie.sharingmap.designsystem.component.Feed
import com.grusie.sharingmap.designsystem.theme.GrayE8EAEB
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.model.FeedUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapFeedModal(
    content: @Composable () -> Unit,
    uiState: MapUiState
) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(20.dp),
        sheetShadowElevation = 20.dp,
        sheetPeekHeight = 348.dp,
        sheetContainerColor = White,
        scaffoldState = scaffoldState,
        sheetDragHandle = { MapBottomSheetDragHandle() },
        sheetContent = {
            MapFeedBottomSheet(
                feeds = uiState.feeds
            )
        },
        content = {
            content()
        }
    )
}

//Design for sheet
@Composable
fun MapFeedBottomSheet(
    feeds: List<FeedUiModel>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
            //.nestedScroll(scrollBehavior.nestedScrollConnection),
        ) {
            items(feeds) {
                Feed(
                    feed = it,
                    isFollow = true,
                    onProfileClick = { /*TODO*/ },
                    onUserClick = { /*TODO*/ },
                    onImageClick = {},
                    onLocationClick = { /*TODO*/ },
                    onArchivingClick = {
//                        isArchivingBottomSheetOpen = !isArchivingBottomSheetOpen
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
    }
}

@Composable
fun MapBottomSheetDragHandle() {
    Spacer(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 20.dp)
            .height(5.dp)
            .width(39.dp)
            .background(GrayE8EAEB)
    )
}

@Composable
@Preview(showBackground = true)
fun MapFeedBottomSheetPreView() {
    MapFeedBottomSheet(feeds = fakeFeeds)
}