package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.ui.model.SearchTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTab(pagerState: PagerState, tabs: List<String>, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = Black,
                height = 1.dp
            )
        }
    ) {
        tabs.forEachIndexed { index, value ->
            Tab(selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            ) {
                Text(
                    text = value,
                    color = Black,
                    style = Typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }

    }

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun CustomTabPreview() {
    val pagerState = rememberPagerState(
        pageCount = { SearchTab.entries.size },
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )
    CustomTab(pagerState = pagerState, tabs = SearchTab.entries.map { it.title })

}