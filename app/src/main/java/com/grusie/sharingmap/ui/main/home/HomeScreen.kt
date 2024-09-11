package com.grusie.sharingmap.ui.main.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CommentContent
import com.grusie.sharingmap.designsystem.component.CustomBottomSheet
import com.grusie.sharingmap.designsystem.component.Feed
import com.grusie.sharingmap.designsystem.component.UserLazyColumn
import com.grusie.sharingmap.designsystem.theme.Gray9A9C9F
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.model.FeedType

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var isModalDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isArchivingBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    val archiveBottomSheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )
    var isCommentBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            FeedTopAppbar(
                scrollBehavior = scrollBehavior,
                hasNotifications = uiState.hasNotifications,
            )
        },
        content = {
            if (uiState.feeds.isNotEmpty()) {
                LazyColumn(
                    contentPadding = it,
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                ) {
                    item {
                        FeedRadioGroup(
                            options = FeedType.entries,
                            selectedType = uiState.selectedFeedType,
                            onClick = viewModel::updateSelectedFeedType,
                        )
                    }
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
                                viewModel.updateSelectedFeed(it)
                            },
                            onMeatBallClick = { isModalDialogOpen = !isModalDialogOpen },
                            onLikeClick = { /*TODO*/ },
                            onChatClick = {
                                isCommentBottomSheetOpen = !isCommentBottomSheetOpen
                                viewModel.updateSelectedFeed(it)
                            },
                            onShareClick = { /*TODO*/ },
                        )
                    }
                }
            } else {
                Column(
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(top = it.calculateTopPadding()),
                ) {
                    FeedRadioGroup(
                        options = FeedType.entries,
                        selectedType = uiState.selectedFeedType,
                        onClick = viewModel::updateSelectedFeedType,
                    )
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

            if (isModalDialogOpen) {
                FeedModal(onDismiss = { isModalDialogOpen = false })
            }

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

            if (isCommentBottomSheetOpen) {
                CustomBottomSheet(
                    title = stringResource(id = R.string.feed_bottom_sheet_comments_title),
                    isEmpty = uiState.selectedFeed?.comments?.isEmpty() ?: true,
                    emptyTitle = stringResource(id = R.string.feed_bottom_sheet_comments_empty_title),
                    content = {
                        CommentContent(
                            comments = uiState.selectedFeed?.comments ?: emptyList()
                        )
                    },
                    sheetState = archiveBottomSheetState,
                    onDismiss = { isCommentBottomSheetOpen = false },
                )
            }

        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedTopAppbar(
    scrollBehavior: TopAppBarScrollBehavior,
    hasNotifications: Boolean,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_feed_logo),
                contentDescription = null,
            )
        },
        actions = {
            Image(
                painter =
                if (hasNotifications) {
                    painterResource(id = R.drawable.btn_notification_alert)
                } else {
                    painterResource(
                        id = R.drawable.btn_notification,
                    )
                },
                contentDescription = null,
                modifier =
                Modifier
                    .clickable { }
                    .padding(end = 14.dp, top = 6.dp, bottom = 6.dp),
            )
        },
        colors =
        TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = White,
            scrolledContainerColor = White,
        ),
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
