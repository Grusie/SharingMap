package com.grusie.sharingmap.ui.main.mypage

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomCreateCancelBottomSheet
import com.grusie.sharingmap.designsystem.component.CustomTab
import com.grusie.sharingmap.designsystem.component.Feed
import com.grusie.sharingmap.designsystem.component.Snackbar
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.main.mypage.archivecollection.NewStorageContent
import com.grusie.sharingmap.ui.main.mypage.archivecollection.StorageLazyColumn
import com.grusie.sharingmap.ui.model.MyPageTab
import com.grusie.sharingmap.ui.model.StorageUiModel
import com.grusie.sharingmap.ui.model.UserUiModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MyPageRoute(
    onUserClick: (UserUiModel) -> Unit,
    onStorageClick: (StorageUiModel) -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val storageBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage.isNotEmpty()) snackbarHostState.showSnackbar(uiState.errorMessage)
    }

    if (uiState.isStorageBottomSheetOpen) {
        CustomCreateCancelBottomSheet(
            title = stringResource(id = R.string.mypage_storages_add_title),
            createText = stringResource(id = R.string.mypage_storage_create_title),
            content = {
                NewStorageContent(
                    textFieldState = viewModel.storageTitleTextField,
                    isLock = uiState.isStorageLock,
                    onLockClick = { viewModel.updateIsStorageLock() })
            },
            sheetState = storageBottomSheetState,
            onDismiss = { viewModel.updateIsStorageBottomSheetOpen(false) },
            onCreateClick = { /*TODO*/ })
    }

    MyPageScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        updateSelectedTabIndex = viewModel::setSelectedTabIndex,
        updateIsStorageBottomSheetOpen = viewModel::updateIsStorageBottomSheetOpen,
        onUserClick = onUserClick,
        onStorageClick = onStorageClick,
    )

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MyPageScreen(
    uiState: MyPageUiState,
    snackbarHostState: SnackbarHostState,
    updateSelectedTabIndex: (Int) -> Unit,
    updateIsStorageBottomSheetOpen: (Boolean) -> Unit,
    onUserClick: (UserUiModel) -> Unit,
    onStorageClick: (StorageUiModel) -> Unit,
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            MyPageTopAppBar(
                name = uiState.user?.name ?: "",
                onSettingClick = {})
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp)
                    .padding(it)
            ) {

                Column {
                    uiState.user?.let {
                        MyUserInfo(user = it)
                        CustomTab(
                            selectedTabIndex = uiState.selectedTabIndex,
                            onClick = updateSelectedTabIndex,
                            tabs = MyPageTab.entries.map { it.title })
                    }

                    if (uiState.isLoading) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).semantics { contentDescription =  context.getString(R.string.loading_prgress)})
                        }
                    } else {
                        if (uiState.selectedTabIndex == 0) {
                            LazyColumn {
                                items(uiState.feeds) {
                                    Feed(
                                        feed = it,
                                        isFollow = true,
                                        onProfileClick = { /*TODO*/ },
                                        onUserClick = {
                                            onUserClick(it)
                                        },
                                        onImageClick = {},
                                        onLocationClick = { /*TODO*/ },
                                        onArchivingClick = {
                                        },
                                        onMeatBallClick = { },
                                        onLikeClick = { /*TODO*/ },
                                        onChatClick = {},
                                        onShareClick = { /*TODO*/ },
                                    )
                                }
                            }
                        } else {
                            StorageLazyColumn(
                                isOwnUser = true,
                                storages = uiState.storages,
                                onAddClick = { updateIsStorageBottomSheetOpen(true) },
                                onClick = {
                                    onStorageClick(it)
                                }
                            )
                        }
                    }
                }

                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp),
                    snackbar = {
                        Snackbar(data = it)
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageTopAppBar(name: String, onSettingClick: () -> Unit, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = name, style = Typography.headlineLarge,
                color = Color.Black
            )
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.btn_setting),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 14.dp)
                    .clickable {
                        onSettingClick()
                    })
        },
        colors =
        TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = White,
            scrolledContainerColor = White,
        ),
    )
}

val previewUserDummy = UserUiModel(
    id = 1L,
    profileImage = "",
    name = "김민수",
    description = "안녕하세요, 반갑습니다",
    email = "",
    followerCount = 50,
    postCount = 20,
    follow = false
)


@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(
        uiState = MyPageUiState(
            user = previewUserDummy
        ),
        snackbarHostState = SnackbarHostState(),
        updateSelectedTabIndex = {},
        updateIsStorageBottomSheetOpen = {},
        onUserClick = {},
        onStorageClick = {}
    )
}


