package com.grusie.sharingmap.ui.main.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.gson.Gson
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomTab
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.model.SearchTab
import com.grusie.sharingmap.ui.navigation.main.NavItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel(), navController: NavController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedTabIndex by viewModel.selectedTabIndex.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.search_title),
                        style = Typography.headlineLarge,
                        color = Color.Black
                    )
                },
                colors =
                TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = White,
                    scrolledContainerColor = White,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.btn_back),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                CustomTab(
                    selectedTabIndex = selectedTabIndex,
                    onClick = { viewModel.setSelectedTabIndex(it) },
                    tabs = SearchTab.entries.map { it.title })
                SearchContent(
                    selectedTabIndex = selectedTabIndex,
                    uiState = uiState,
                    searchText = viewModel.searchTextField,
                    onUserItemClick = {
                        viewModel.insertUserSearchHistory(it)
                        navController.navigate(
                            NavItem.User.screenRoute + "?user=${
                                Gson().toJson(
                                    it
                                )
                            }"
                        )
                    },
                    onUserHistoryDelete = viewModel::deleteAllUserSearchHistory,
                    onTagItemClick = {
                        viewModel.insertTagSearchHistory(it)
                        navController.navigate(
                            NavItem.FeedCollection.screenRoute + "?tag=${
                                Gson().toJson(
                                    it
                                )
                            }"
                        )
                    },
                    onTagHistoryDelete = viewModel::deleteAllTagSearchHistory
                )
            }
        }
    )
}