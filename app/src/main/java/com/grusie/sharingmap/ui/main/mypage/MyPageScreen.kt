package com.grusie.sharingmap.ui.main.mypage

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.gson.Gson
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomCreateCancelBottomSheet
import com.grusie.sharingmap.designsystem.component.CustomTab
import com.grusie.sharingmap.designsystem.component.Feed
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.main.mypage.archivecollection.NewStorageContent
import com.grusie.sharingmap.ui.main.mypage.archivecollection.StorageLazyColumn
import com.grusie.sharingmap.ui.model.MyPageTab
import com.grusie.sharingmap.ui.navigation.main.NavItem


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPageScreen(viewModel: MyPageViewModel = hiltViewModel(), navController: NavController) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isStorageBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    val storageBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    var isLock by rememberSaveable { mutableStateOf(false) }
    val selectedTabIndex by viewModel.selectedTabIndex.collectAsStateWithLifecycle()

    when(uiState) {
        is MyPageUiState.Loading -> {}
        is MyPageUiState.Success -> {
            Scaffold(
                topBar = {
                    MyPageTopAppBar(name = (uiState as MyPageUiState.Success).user.name, onSettingClick = {})
                },
                content = {
                    Column(modifier = Modifier.padding(it)) {
                        MyUserInfo(user = (uiState as MyPageUiState.Success).user)
                        CustomTab(
                            selectedTabIndex = selectedTabIndex,
                            onClick = viewModel::setSelectedTabIndex,
                            tabs = MyPageTab.entries.map { it.title })
                        if (selectedTabIndex == 0) {
                            LazyColumn {
                                items((uiState as MyPageUiState.Success).feeds) {
                                    Feed(
                                        feed = it,
                                        isFollow = true,
                                        onProfileClick = { /*TODO*/ },
                                        onUserClick = {
                                            navController.navigate(
                                                NavItem.User.screenRoute + "?user=${
                                                    Gson().toJson(
                                                        it
                                                    )
                                                }"
                                            )
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
                                storages = (uiState as MyPageUiState.Success).storages,
                                onAddClick = { isStorageBottomSheetOpen = true },
                                onClick = {
                                    navController.navigate(
                                        NavItem.FeedCollection.screenRoute + "?storage=${
                                            Gson().toJson(
                                                it
                                            )
                                        }"
                                    )
                                }
                            )

                            if (isStorageBottomSheetOpen) {
                                CustomCreateCancelBottomSheet(
                                    title = stringResource(id = R.string.mypage_storages_add_title),
                                    createText = stringResource(id = R.string.mypage_storage_create_title),
                                    content = {
                                        NewStorageContent(
                                            textFieldState = viewModel.storageTitleTextField,
                                            isLock = isLock,
                                            onLockClick = { isLock = !isLock })
                                    },
                                    sheetState = storageBottomSheetState,
                                    onDismiss = { isStorageBottomSheetOpen = false },
                                    onCreateClick = { /*TODO*/ })
                            }
                        }
                    }
                }
            )
        }
        is MyPageUiState.Error -> {}
    }
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


