package com.grusie.sharingmap.ui.main.search

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.gson.Gson
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomTab
import com.grusie.sharingmap.designsystem.component.Snackbar
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.model.SearchTab
import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel
import com.grusie.sharingmap.ui.navigation.main.NavItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigationClick: () -> Unit,
    onUserItemClick: (UserUiModel) -> Unit,
    onTagItemClick: (TagUiModel) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(uiState.errorMessage)
        }
    }

    SearchScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        searchTextField = viewModel.searchTextField,
        updateSelectedTabIndex = viewModel::setSelectedTabIndex,
        insertUserSearchHistory = viewModel::insertUserSearchHistory,
        insertTagSearchHistory = viewModel::insertTagSearchHistory,
        deleteAllUerSearchHistory = viewModel::deleteAllUserSearchHistory,
        deleteAllTagSearchHistory = viewModel::deleteAllTagSearchHistory,
        onNavigationClick = onNavigationClick,
        onUserItemClick = onUserItemClick,
        onTagItemClick = onTagItemClick
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    snackbarHostState: SnackbarHostState,
    searchTextField: TextFieldState,
    updateSelectedTabIndex: (Int) -> Unit,
    insertUserSearchHistory: (UserUiModel) -> Unit,
    insertTagSearchHistory: (TagUiModel) -> Unit,
    deleteAllUerSearchHistory: () -> Unit,
    deleteAllTagSearchHistory: () -> Unit,
    onNavigationClick: () -> Unit,
    onUserItemClick: (UserUiModel) -> Unit,
    onTagItemClick: (TagUiModel) -> Unit
) {

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
                    IconButton(onClick = onNavigationClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.btn_back),
                            tint = Black,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                Column {
                    CustomTab(
                        selectedTabIndex = uiState.selectedTabIndex,
                        onClick = updateSelectedTabIndex,
                        tabs = SearchTab.entries.map { it.title })
                    SearchContent(
                        selectedTabIndex = uiState.selectedTabIndex,
                        uiState = uiState,
                        searchText = searchTextField,
                        onUserItemClick = {
                            insertUserSearchHistory(it)
                            onUserItemClick(it)
                        },
                        onUserHistoryDelete = deleteAllUerSearchHistory,
                        onTagItemClick = {
                            insertTagSearchHistory(it)
                            onTagItemClick(it)
                        },
                        onTagHistoryDelete = deleteAllTagSearchHistory,
                    )
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

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun SearchScreenPreview(modifier: Modifier = Modifier) {
    SearchScreen(
        uiState = SearchUiState(),
        snackbarHostState = SnackbarHostState(),
        searchTextField = TextFieldState(),
        updateSelectedTabIndex =  {},
        insertUserSearchHistory = {},
        insertTagSearchHistory = {},
        deleteAllUerSearchHistory = { /*TODO*/ },
        deleteAllTagSearchHistory = { /*TODO*/ },
        onNavigationClick = { /*TODO*/ },
        onUserItemClick = {},
        onTagItemClick = {}
    )
}