package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grusie.sharingmap.designsystem.theme.Black
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
