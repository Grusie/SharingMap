package com.grusie.sharingmap.ui.main.mypage.archivecollection

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CommentContent
import com.grusie.sharingmap.designsystem.component.CustomBottomSheet
import com.grusie.sharingmap.designsystem.component.Feed
import com.grusie.sharingmap.designsystem.component.UserLazyColumn
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.main.home.FeedModal
import com.grusie.sharingmap.ui.model.StorageUiModel
import com.grusie.sharingmap.ui.model.TagUiModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArchiveCollectionScreen(
    navController: NavController,
    viewModel: ArchiveCollectionViewModel = hiltViewModel(),
    storage: StorageUiModel?,
    tag: TagUiModel?
) {

    var isModalDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isArchivingBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    val archiveBottomSheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )
    var isCommentBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    val uiState: ArchiveCollectionUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedFeed by viewModel.selectedFeed.collectAsStateWithLifecycle()

    tag?.let { viewModel.updateFeedCollection(it) }
    storage?.let { viewModel.updateFeedCollection(it) }

    Scaffold(
        topBar = {
            FeedCollectionTopAppBar(
                storageName = tag?.name ?: storage!!.title,
                navigateBack = { navController.popBackStack() })
        },
        content = {
            when (uiState) {
                is ArchiveCollectionUiState.Loading -> {

                }

                is ArchiveCollectionUiState.Success -> {
                    LazyColumn(
                        contentPadding = it
                    ) {
                        items((uiState as ArchiveCollectionUiState.Success).feeds) {
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


                    if (isModalDialogOpen) {
                        FeedModal(onDismiss = { isModalDialogOpen = false })
                    }

                    if (isArchivingBottomSheetOpen) {
                        CustomBottomSheet(
                            title = stringResource(id = R.string.feed_bottom_sheet_archivings_title),
                            isEmpty = selectedFeed?.archivings?.isEmpty() ?: true,
                            emptyTitle = stringResource(id = R.string.feed_bottom_sheet_archivings_empty_title),
                            content = {
                                UserLazyColumn(
                                    users = selectedFeed?.archivings ?: emptyList()
                                )
                            },
                            sheetState = archiveBottomSheetState,
                            onDismiss = { isArchivingBottomSheetOpen = false },
                        )
                    }

                    if (isCommentBottomSheetOpen) {
                        CustomBottomSheet(
                            title = stringResource(id = R.string.feed_bottom_sheet_comments_title),
                            isEmpty = selectedFeed?.comments?.isEmpty() ?: true,
                            emptyTitle = stringResource(id = R.string.feed_bottom_sheet_comments_empty_title),
                            content = {
                                CommentContent(
                                    comments = selectedFeed?.comments ?: emptyList()
                                )
                            },
                            sheetState = archiveBottomSheetState,
                            onDismiss = { isCommentBottomSheetOpen = false },
                        )
                    }

                }

                is ArchiveCollectionUiState.Error -> {

                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedCollectionTopAppBar(
    storageName: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = storageName, style = Typography.headlineLarge, color = Black)
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(painter = painterResource(id = R.drawable.btn_back), contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = White,
        ),
    )
}