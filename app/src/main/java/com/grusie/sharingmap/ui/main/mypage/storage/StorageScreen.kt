package com.grusie.sharingmap.ui.main.mypage.storage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
import com.grusie.sharingmap.ui.main.home.FeedModal
import com.grusie.sharingmap.ui.model.StorageUiModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StorageScreen(navController: NavController, viewModel: StorageViewModel = hiltViewModel(), storage: StorageUiModel) {

    var isModalDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isArchivingBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    val archiveBottomSheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )
    var isCommentBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    val uiState: StorageUiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.updateStorage(storage)
    Scaffold(
        topBar = { StorageTopAppBar(storageName = uiState.storage.title, navigateBack = { navController.popBackStack() })},
        content = {
            Column(modifier = Modifier.padding(it)) {
                Spacer(modifier = Modifier.height(24.dp))
                LazyColumn {
                    items(uiState.storage.feeds) {
                        Feed(
                            feed = it,
                            isFollow = true,
                            onProfileClick = { /*TODO*/ },
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
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageTopAppBar(storageName: String, navigateBack: () -> Unit, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = {
        Text(text = storageName, style = Typography.headlineLarge, color = Black)
    }, navigationIcon = {
        IconButton(onClick = navigateBack) {
            Icon(painter = painterResource(id = R.drawable.btn_back), contentDescription = null)
        }
    })
}