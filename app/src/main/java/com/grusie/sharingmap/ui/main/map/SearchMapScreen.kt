package com.grusie.sharingmap.ui.main.map

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomTextFieldWithBackground
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Black000000
import com.grusie.sharingmap.designsystem.theme.Gray8D8D8E
import com.grusie.sharingmap.designsystem.theme.GrayE6E6E6
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce

@OptIn(ExperimentalFoundationApi::class, FlowPreview::class)
@Composable
fun SearchMapScreen(
    navController: NavController = rememberNavController(),
    searchMapViewModel: SearchRegionViewModel = hiltViewModel()
) {
    val uiState by searchMapViewModel.uiState.collectAsStateWithLifecycle()
    val searchRegionList by searchMapViewModel.searchRegionList.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            SearchTopBar(finish = { navController.popBackStack() })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 16.dp)
        ) {
            val textState = rememberTextFieldState()

            LaunchedEffect(textState.text) {
                snapshotFlow { textState.text }
                    .debounce(300)
                    .collect { query ->
                        if (query.isNotEmpty()) {
                            searchMapViewModel.getSearchRegionList(query.toString())
                        }
                    }
            }

            CustomTextFieldWithBackground(
                textFieldState = textState,
                hintText = stringResource(id = R.string.search_map_hint),
            )

            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.search_history),
                        fontSize = 14.sp,
                        color = Black
                    )
                    Text(
                        modifier = Modifier.clickable { },
                        text = stringResource(id = R.string.search_delete_history),
                        fontSize = 14.sp,
                        color = Black
                    )
                }
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 20.dp),
                    color = GrayE6E6E6
                )

                LazyColumn {
                    items(searchRegionList) { searchRegion ->
                        SearchHistoryItem(
                            keyword = searchRegion.placeName ?: "",
                            address = searchRegion.address ?: ""
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchTopBar(finish: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 14.dp),
            onClick = { finish() },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.btn_back),
                contentDescription = "SearchMapBackBtn"
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = stringResource(id = R.string.search_map_title),
            color = Black000000,
            style = TextStyle(fontWeight = FontWeight(700)),
            fontSize = 16.sp,
        )
    }
}

@Composable
fun SearchHistoryItem(keyword: String, address: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 12.dp, horizontal = 20.dp)
    ) {
        Text(
            text = keyword,
            color = Black,
            fontSize = 14.sp,
            style = TextStyle(fontWeight = FontWeight(500))
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = address,
            fontSize = 13.sp,
            color = Gray8D8D8E,
            style = TextStyle(fontWeight = FontWeight(500))
        )
    }
}

@Composable
@Preview(backgroundColor = 0xffffff, showBackground = true)
fun SearchTopBarPreview() {
    SearchTopBar()
}

@Composable
@Preview(backgroundColor = 0xffffff, showBackground = true)
fun SearchMapScreenPreview() {
    SearchMapScreen()
}

@Composable
@Preview(backgroundColor = 0xffffff, showBackground = true)
fun SearchHistoryItemPreview() {
    SearchHistoryItem(keyword = "맥도날드 서울역점", address = "서울 용산구 한강대로 405 서울역(철도역)")
}