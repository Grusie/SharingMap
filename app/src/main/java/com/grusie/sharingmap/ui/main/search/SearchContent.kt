package com.grusie.sharingmap.ui.main.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomTextField
import com.grusie.sharingmap.designsystem.component.UserLazyColumn
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.GrayE6E6E6
import com.grusie.sharingmap.designsystem.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchContent(selectedTabIndex: Int, modifier: Modifier = Modifier) {
    SearchContentItem(lazyColumn = {
        if (selectedTabIndex == 0) UserLazyColumn(
            users = listOf(),
            isBottomSheet = true,
            modifier = Modifier.fillMaxHeight()
        ) else {
            TagLazyColumn(
                tags =
                listOf(),
                modifier = Modifier.fillMaxHeight()
            )
        }
    })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchContentItem(lazyColumn: @Composable () -> Unit, modifier: Modifier = Modifier) {
    val search = rememberTextFieldState()
    Column {
        Spacer(modifier = modifier.height(16.dp))
        CustomTextField(
            textFieldState = search,
            hintText = stringResource(id = R.string.search_title)
        )
        Spacer(modifier = modifier.height(16.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (search.text.isEmpty()) stringResource(id = R.string.search_history_title) else stringResource(
                    id = R.string.search_result_title
                ),
                style = Typography.titleSmall,
            )
            if (search.text.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.search_history_remove_title),
                    style = Typography.titleSmall,
                )
            }
        }
        Spacer(modifier = Modifier.height(9.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = GrayE6E6E6,
            modifier = modifier.padding(horizontal = 20.dp)
        )
        lazyColumn()

    }
}

@Composable
fun TagLazyColumn(tags: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        itemsIndexed(tags) { _, tag ->
            Text(
                text = tag,
                style = Typography.titleSmall,
                color = Black,
                modifier = modifier.padding(vertical = 17.dp, horizontal = 20.dp)
            )
        }
    }
}

@Preview
@Composable
private fun SearchContentPreview() {
}