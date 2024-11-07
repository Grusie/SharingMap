package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.GrayBABBBC
import com.grusie.sharingmap.designsystem.theme.GrayD9D9D9
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.ui.model.UserUiModel

@Composable
fun UserLazyColumn(
    users: List<UserUiModel>,
    isBottomSheet: Boolean = true,
    onClick: (UserUiModel) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = if (isBottomSheet) modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f) else modifier.fillMaxSize()
    ) {
        itemsIndexed(users) { _, user ->
            UserItem(user, onClick = onClick)
        }
    }
}

@Composable
fun UserItem(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    onClick: (UserUiModel) -> Unit,
) {
    Row(modifier = modifier
        .padding(vertical = 8.dp, horizontal = 20.dp)
        .clickable {
            onClick(user)
        }) {
        AsyncImage(
            model = user.profileImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =
            Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = user.name,
            style = Typography.bodySmall,
            color = Black,
            modifier =
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
        )
    }
}

@Composable
fun UserItemWithCount(user: UserUiModel, modifier: Modifier = Modifier) {
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = modifier.fillMaxWidth()) {
            AsyncImage(
                model = user.profileImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = user.name,
                    style = Typography.bodySmall,
                    color = Black,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(
                            id = R.string.mypage_user_info_follower_count_text,
                            user.followerCount
                        ),
                        style = Typography.bodyMedium,
                        color = GrayBABBBC,
                    )
                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .width(2.dp)
                            .height(2.dp)
                            .clip(
                                RoundedCornerShape(300.dp)
                            )
                            .align(Alignment.CenterVertically),
                        thickness = 2.dp,
                        color = GrayD9D9D9
                    )
                    Text(
                        text = stringResource(
                            id = R.string.mypage_user_info_post_count_text,
                            user.postCount
                        ),
                        style = Typography.bodyMedium,
                        color = GrayBABBBC
                    )
                }
            }
        }
    }
}

@Composable
fun UserInfo(user: UserUiModel, modifier: Modifier = Modifier) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    Column {
        UserItemWithCount(user)
        if (user.description?.isNotEmpty() == true) {
            LimitedText(
                text = user.description ?: "",
                isExpanded = isExpanded,
                onClick = { isExpanded = !isExpanded },
                modifier = modifier.padding(16.dp)
            )
        }
    }

}

@Preview
@Composable
private fun UserItemWithCountPreview() {
    UserInfo(
        user = UserUiModel(
            id = 1,
            name = "김민수",
            description = "안녕하세요. 김민수입니다. 잘 부탁드립니다. 안녕하세요. 김민수입니다. 잘 부탁드립니다. 안녕하세요. 김민수입니다. 잘 부탁드립니다. 안녕하세요. 김민수입니다. 잘 부탁드립니다.",
            followerCount = 100,
            postCount = 10,
        )
    )
}
