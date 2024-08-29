package com.grusie.sharingmap.ui.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.Feed
import com.grusie.sharingmap.designsystem.theme.Gray9A9C9F
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.ui.model.FeedType

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            FeedTopAppbar(
                scrollBehavior = scrollBehavior,
                hasNotifications = uiState.hasNotifications,
            )
        },
        content = {
            if (uiState.feeds.isNotEmpty()) {
                LazyColumn(
                    modifier =
                        Modifier
                            .padding(top = it.calculateTopPadding())
                            .fillMaxSize()
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
                ) {
                    item {
                        FeedRadioGroup(
                            options = FeedType.entries,
                            selectedType = uiState.selectedFeedType,
                            onClick = viewModel::updateSelectedFeedType,
                        )
                    }
                    items(uiState.feeds) {
                        Feed(
                            feed = it,
                            isFollow = true,
                            onProfileClick = { /*TODO*/ },
                            onImageClick = {},
                            onLocationClick = { /*TODO*/ },
                            onArchivingClick = { /*TODO*/ },
                            onMeatBallClick = { /*TODO*/ },
                            onLikeClick = { /*TODO*/ },
                            onChatClick = { /*TODO*/ },
                            onShareClick = { /*TODO*/ },
                        )
                    }
                }
            } else {
                Column(modifier = Modifier.fillMaxSize().padding(top = it.calculateTopPadding())) {
                    FeedRadioGroup(
                        options = FeedType.entries,
                        selectedType = uiState.selectedFeedType,
                        onClick = viewModel::updateSelectedFeedType,
                    )
                    Text(
                        text = stringResource(id = R.string.feed_empty_text),
                        textAlign = TextAlign.Center,
                        style = Typography.headlineSmall,
                        color = Gray9A9C9F,
                        modifier = Modifier.fillMaxSize().wrapContentHeight(Alignment.CenterVertically),
                    )
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedTopAppbar(
    scrollBehavior: TopAppBarScrollBehavior,
    hasNotifications: Boolean,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_feed_logo),
                contentDescription = null,
            )
        },
        actions = {
            Image(
                painter =
                    if (hasNotifications) {
                        painterResource(id = R.drawable.btn_notification_alert)
                    } else {
                        painterResource(
                            id = R.drawable.btn_notification,
                        )
                    },
                contentDescription = null,
                modifier = Modifier.clickable { }.padding(end = 14.dp, top = 6.dp, bottom = 6.dp),
            )
        },
        colors =
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
            ),
        scrollBehavior = scrollBehavior,
    )
}
