package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Gray200
import com.grusie.sharingmap.designsystem.theme.Gray200_30
import com.grusie.sharingmap.designsystem.theme.Gray400
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.util.singleClickable
import com.grusie.sharingmap.ui.model.FeedInfoUiModel
import com.grusie.sharingmap.ui.model.FeedUiModel
import com.grusie.sharingmap.ui.model.LocationUiModel

@Composable
fun Feed(
    feed: FeedUiModel,
    onProfileClick: () -> Unit,
    onImageClick: (String) -> Unit,
    onLocationClick: () -> Unit,
    onArchivingClick: () -> Unit,
    onMeatBallClick: () -> Unit,
    onLikeClick: () -> Unit,
    onChatClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
        ) {
            ProfileImage(feed.profileImage, onProfileClick)
            Spacer(modifier = Modifier.width(9.dp))
            Column(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 1.dp),
            ) {
                Text(
                    text = feed.title,
                    style = Typography.bodySmall,
                    color = Black,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = feed.date,
                    style = Typography.labelSmall,
                    color = Gray400,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.btn_meatball),
                contentDescription = null,
                modifier = Modifier.singleClickable { onMeatBallClick() },
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        FeedRow(
            feed = feed,
            onImageClick = onImageClick,
            onLocationClick = onLocationClick,
            onArchivingClick = onArchivingClick,
            onLikeClick = onLikeClick,
            onChatClick = onChatClick,
            onShareClick = onShareClick,
            modifier = Modifier.padding(horizontal = 14.dp),
        )
    }
}

@Composable
fun ProfileImage(
    profileImage: String,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier.size(34.dp)) {
        AsyncImage(
            model = profileImage,
            contentDescription = null,
            modifier =
                Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
        )
        Image(
            painter = painterResource(id = R.drawable.btn_add_small),
            contentDescription = null,
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 0.5.dp, y = 0.5.dp)
                    .singleClickable { onAddClick() },
        )
    }
}

@Composable
fun FeedRow(
    feed: FeedUiModel,
    onImageClick: (String) -> Unit,
    onLocationClick: () -> Unit,
    onArchivingClick: () -> Unit,
    onLikeClick: () -> Unit,
    onChatClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SubcomposeLayout { constraints ->
        val dividerMeasurable =
            subcompose("divider") {
                ArchivingList(onArchivingClick = onArchivingClick)
            }.map { it.measure(constraints) }

        val dividerWidth = dividerMeasurable.maxOf { it.width }
        val columnMeasurable =
            subcompose("column") {
                FeedContent(
                    feed,
                    onImageClick,
                    onLocationClick,
                    onLikeClick,
                    onChatClick,
                    onShareClick,
                )
            }.map { it.measure(constraints.copy(maxWidth = constraints.maxWidth - dividerWidth)) }

        val columnHeight = columnMeasurable.maxOf { it.height }

        val adjustedDividerConstraints = constraints.copy(maxHeight = columnHeight - 160)
        val adjustedDividerMeasurable =
            subcompose("adjustedDivider") {
                ArchivingList(onArchivingClick = onArchivingClick)
            }.map { it.measure(adjustedDividerConstraints) }

        layout(constraints.maxWidth, columnHeight) {
            adjustedDividerMeasurable.forEach {
                it.placeRelative(0, 0)
            }

            columnMeasurable.forEach {
                it.placeRelative(dividerWidth, 0)
            }
        }
    }
}

@Composable
fun FeedContent(
    feed: FeedUiModel,
    onImageClick: (String) -> Unit,
    onLocationClick: () -> Unit,
    onLikeClick: () -> Unit,
    onChatClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(
            text = feed.content,
            style = Typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth().padding(end = 14.dp),
        )
        Spacer(modifier = Modifier.height(12.dp))
        if (feed.contentImages.size == 1) {
            SingleContentImage(
                contentImage = feed.contentImages[0],
                onImageClick = onImageClick,
            )
        } else {
            LazyRow(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(end = 14.dp),
            ) {
                itemsIndexed(feed.contentImages) { _, item ->
                    ContentImage(contentImage = item, onImageClick = onImageClick)
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Location(
            location = feed.locationUiModel,
            onLocationClick = onLocationClick,
            modifier = Modifier.padding(end = 14.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        FeedInfo(
            feedInfo = feed.feedInfoUiModel,
            onLikeClick = onLikeClick,
            onChatClick = onChatClick,
            onShareClick = onShareClick,
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ContentImage(
    contentImage: String,
    onImageClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = contentImage,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier =
            modifier
                .size(192.dp)
                .clip(RoundedCornerShape(7.dp))
                .singleClickable { onImageClick(contentImage) },
    )
}

@Composable
fun SingleContentImage(
    contentImage: String,
    onImageClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = contentImage,
        contentDescription = null,
        modifier =
            modifier
                .fillMaxWidth()
                .height(192.dp)
                .clip(RoundedCornerShape(7.dp))
                .singleClickable { onImageClick(contentImage) },
    )
}

@Composable
fun Location(
    location: LocationUiModel,
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit = {},
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .border(1.dp, color = Gray200, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .background(color = Gray200_30)
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .singleClickable { onLocationClick() },
    ) {
        Text(text = location.name, style = Typography.titleMedium, color = Black)
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = location.address, style = Typography.labelMedium, color = Gray400)
    }
}

@Composable
fun FeedInfo(
    feedInfo: FeedInfoUiModel,
    onLikeClick: () -> Unit,
    onChatClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        FeedInfoItem(
            selectIcon = R.drawable.btn_like_fill,
            unselectIcon = R.drawable.btn_like,
            count = feedInfo.likeCount,
            onClick = onLikeClick,
        )
        FeedInfoItem(
            selectIcon = R.drawable.btn_chat_fill,
            unselectIcon = R.drawable.btn_chat,
            count = feedInfo.chatCount,
            onClick = onChatClick,
        )
        FeedInfoItem(
            selectIcon = R.drawable.btn_share,
            unselectIcon = R.drawable.btn_share,
            count = feedInfo.shareCount,
            onClick = onShareClick,
        )
    }
}

@Composable
fun FeedInfoItem(
    selectIcon: Int,
    unselectIcon: Int,
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isClicked by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier =
            modifier.width(80.dp).singleClickable {
                onClick()
                isClicked = !isClicked
            },
    ) {
        Image(
            painter = painterResource(id = if (isClicked) selectIcon else unselectIcon),
            contentDescription = "좋아요 수",
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = if (isClicked) (count + 1).toString() else count.toString(),
            style = Typography.bodySmall,
            color = Gray400,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}

@Composable
fun ArchivingList(
    onArchivingClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = Modifier.padding(start = 14.dp)) {
        VerticalDivider(
            modifier =
                Modifier
                    .padding(start = 16.dp, end = 14.dp)
                    .fillMaxHeight(0.9f),
            thickness = 2.dp,
            color = Gray200,
        )
        Image(
            painter = painterResource(id = R.drawable.btn_default_sharelist_image),
            contentDescription = null,
            modifier =
                Modifier.padding(start = 8.dp, end = 17.dp, top = 4.dp).singleClickable {
                    onArchivingClick()
                },
        )
    }
}

@Preview
@Composable
private fun FeedPreview() {
    Feed(
        FeedUiModel(
            profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
            title = "vocent",
            date = "2024.04.17",
            content = "honestatis",
            contentImages =
                listOf(
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                ),
            locationUiModel =
                LocationUiModel(
                    name = "Lilian Douglas",
                    address = "taciti",
                ),
            feedInfoUiModel =
                FeedInfoUiModel(
                    likeCount = 2617,
                    chatCount = 1606,
                    shareCount = 8829,
                ),
        ),
        onProfileClick = {},
        onImageClick = {},
        onArchivingClick = {},
        onLocationClick = {},
        onMeatBallClick = {},
        onLikeClick = {},
        onChatClick = {},
        onShareClick = {},
    )
}
