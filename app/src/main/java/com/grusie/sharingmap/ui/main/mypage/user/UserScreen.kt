package com.grusie.sharingmap.ui.main.mypage.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.gson.Gson
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomTab
import com.grusie.sharingmap.designsystem.component.Feed
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.main.mypage.OtherUserInfo
import com.grusie.sharingmap.ui.main.mypage.StorageLazyColumn
import com.grusie.sharingmap.ui.model.MyPageTab
import com.grusie.sharingmap.ui.model.UserUiModel
import com.grusie.sharingmap.ui.navigation.main.NavItem

@Composable
fun UserScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel(),
    user: UserUiModel
) {

    val uiState: UserUiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.updateUser(user)

    Scaffold(
        topBar = {
            UserTopAppBar(
                name = uiState.user.name,
                navigateBack = { navController.popBackStack() },
                onMeatballClick = { /*TODO*/ })
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                OtherUserInfo(user = user, isFollow = true, onFollowClick = {}, onMapClick = {})
                CustomTab(
                    selectedTabIndex = uiState.selectedTabIndex,
                    onClick = viewModel::setSelectedTabIndex,
                    tabs = MyPageTab.entries.map { it.title })
                if (uiState.selectedTabIndex == 0) {
                    LazyColumn {
                        items(uiState.feeds) {
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
                        isOwnUser = false,
                        storages = uiState.storages,
                        onAddClick = { },
                        onClick = {
                            navController.navigate(
                                NavItem.Storage.screenRoute + "?storage=${
                                    Gson().toJson(
                                        it
                                    )
                                }"
                            )
                        }
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