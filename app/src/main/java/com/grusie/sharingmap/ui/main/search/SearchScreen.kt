package com.grusie.sharingmap.ui.main.search

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomTab
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.ui.model.SearchTab

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.search_title),
                    style = Typography.headlineLarge,
                    color = Color.Black
                )
            })
        },
        content = {
            Column(modifier = Modifier.padding(top = it.calculateTopPadding())) {
                CustomTab(
                    selectedTabIndex = selectedTabIndex,
                    onClick = { selectedTabIndex = it },
                    tabs = SearchTab.entries.map { it.title })
                SearchContent(
                    selectedTabIndex = selectedTabIndex,
                    uiState = uiState,
                    searchText = viewModel.searchTextField,
                    onUserItemClick = {
                        viewModel.insertUserSearchHistory(it)
                    },
                    onUserHistoryDelete = viewModel::deleteAllUserSearchHistory
                )
            }
        }
    )
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen()
}