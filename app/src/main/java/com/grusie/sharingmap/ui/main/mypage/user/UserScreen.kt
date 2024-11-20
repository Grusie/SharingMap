package com.grusie.sharingmap.ui.main.mypage.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomTab
import com.grusie.sharingmap.designsystem.component.Feed
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.main.mypage.OtherUserInfo
import com.grusie.sharingmap.ui.main.mypage.archivecollection.StorageLazyColumn
import com.grusie.sharingmap.ui.model.MyPageTab
import com.grusie.sharingmap.ui.model.StorageUiModel

@Composable
fun UserRoute(
    userId: Long,
    viewModel: UserViewModel = hiltViewModel(),
    onNavigateClick: () -> Unit,
    onUserClick: (Long) -> Unit,
    onStorageClick: (StorageUiModel) -> Unit
) {
    val uiState: UserUiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.getUser(userId)

    UserScreen(
        uiState = uiState,
        onNavigateClick = onNavigateClick,
        onUserClick = onUserClick,
        onStorageClick = onStorageClick,
        updateSelectedTabIndex = viewModel::updateSelectedTabIndex,
        updateIsFollow = viewModel::updateIsFollow
    )


}

@Composable
fun UserScreen(
    uiState: UserUiState,
    onNavigateClick: () -> Unit,
    onUserClick: (Long) -> Unit,
    onStorageClick: (StorageUiModel) -> Unit,
    updateSelectedTabIndex: (Int) -> Unit,
    updateIsFollow: () -> Unit,
    ) {
    Scaffold(
        topBar = {
            UserTopAppBar(
                name = uiState.user.name,
                navigateBack = onNavigateClick,
                onMeatballClick = { /*TODO*/ })
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                OtherUserInfo(
                    user = uiState.user,
                    isFollow = uiState.isFollow,
                    onFollowClick = updateIsFollow,
                    onMapClick = {})
                CustomTab(
                    selectedTabIndex = uiState.selectedTabIndex,
                    onClick = updateSelectedTabIndex,
                    tabs = MyPageTab.entries.map { it.title })
                if (uiState.selectedTabIndex == 0) {
                    LazyColumn {
                        items(uiState.feeds) {
                            Feed(
                                feed = it,
                                isFollow = true,
                                onProfileClick = { /*TODO*/ },
                                onUserClick = onUserClick,
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
                        isOwnUser = false,
                        storages = uiState.storages,
                        onAddClick = { },
                        onClick = onStorageClick
                    )
                }
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopAppBar(
    name: String,
    navigateBack: () -> Unit,
    onMeatballClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = name, style = Typography.headlineLarge,
                color = Black
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(painter = painterResource(id = R.drawable.btn_back), contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = White,
        ),
        actions = {
            Image(
                painter = painterResource(id = R.drawable.btn_meatball),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 14.dp)
                    .clickable {
                        onMeatballClick()
                    })
        },
    )

}