package com.grusie.sharingmap.ui.main.mypage

import android.annotation.SuppressLint
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomTab
import com.grusie.sharingmap.designsystem.component.Feed
import com.grusie.sharingmap.designsystem.component.ModalTwoLinesItem
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.ui.model.MyPageTab

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPageScreen(viewModel: MyPageViewModel = hiltViewModel()) {

    val uiState: MyPageUiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            MyPageTopAppBar(onSettingClick = {})
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                MyUserInfo(user = uiState.user)
                CustomTab(selectedTabIndex = uiState.selectedTabIndex, onClick = viewModel::setSelectedTabIndex, tabs = MyPageTab.entries.map { it.title })
                if(uiState.selectedTabIndex == 0)  {
                    LazyColumn {
                        items(uiState.feeds) {
                            Feed(
                                feed = it,
                                isFollow = true,
                                onProfileClick = { /*TODO*/ },
                                onImageClick = {},
                                onLocationClick = { /*TODO*/ },
                                onArchivingClick = {
                                },
                                onMeatBallClick = {  },
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
                        onAddClick = { /*TODO*/ },
                        onClick = {}
                    )
                }
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageTopAppBar(onSettingClick: () -> Unit, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "아이디", style = Typography.headlineLarge,
                color = Color.Black
            )
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.btn_setting),
                contentDescription = null,
                modifier = Modifier.padding(end = 14.dp).clickable {
                    onSettingClick()
                })
        },
    )
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    MyPageScreen()
}


