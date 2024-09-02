package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.ui.model.UserUiModel

@Composable
fun UserLazyColumn(
    users: List<UserUiModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6f)) {
        itemsIndexed(users) { index, user ->
            UserItem(user)
        }
    }
}

@Composable
fun UserItem(
    user: UserUiModel,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.padding(vertical = 8.dp, horizontal = 20.dp)) {
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

@Preview
@Composable
private fun UserItemPreview() {
    UserLazyColumn(users = listOf(UserUiModel(1, "", "김민수"), UserUiModel(2, "", "신나라")))
}
