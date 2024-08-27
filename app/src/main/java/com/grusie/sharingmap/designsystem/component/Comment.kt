package com.grusie.sharingmap.designsystem.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Gray400
import com.grusie.sharingmap.designsystem.theme.GrayA8AAAB
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.util.singleClickable
import com.grusie.sharingmap.ui.model.CommentUiModel
import com.grusie.sharingmap.ui.model.UserUiModel
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CommentContent(
    comments: List<CommentUiModel>,
    modifier: Modifier = Modifier,
) {
    val comment = rememberTextFieldState()
    Column(modifier = modifier.fillMaxWidth()) {
        CommentLazyColumn(comments = comments)
        CustomTextField(textFieldState = comment)
        Spacer(modifier = Modifier.height(34.dp))
    }
}

@Composable
fun CommentLazyColumn(
    comments: List<CommentUiModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)) {
        itemsIndexed(comments) { _, comment ->
            CommentItem(comment = comment, isFollow = true, onMeatBallClick = {}, onFollowClick = {})
        }
    }
}

@Composable
fun CommentItem(
    comment: CommentUiModel,
    isFollow: Boolean,
    onMeatBallClick: () -> Unit,
    onFollowClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
        ) {
            ProfileImage(profileImage = comment.user.profileImage, isFollow = isFollow, onAddClick = { onFollowClick() })
            Spacer(modifier = Modifier.width(9.dp))
            Column(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 1.dp),
            ) {
                Text(
                    text = comment.user.name,
                    style = Typography.bodySmall,
                    color = Black,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = comment.date.toString(),
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
        Text(
            text = comment.content,
            style = Typography.bodyMedium,
            modifier =
                modifier
                    .fillMaxWidth()
                    .padding(start = 57.dp, end = 14.dp),
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "댓글달기", style = Typography.bodyMedium, color = GrayA8AAAB, modifier = modifier.padding(start = 57.dp, end = 14.dp))
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun CommentPreview() {
    CommentLazyColumn(
        comments =
            listOf(
                CommentUiModel(
                    id = 1,
                    user =
                        UserUiModel(
                            id = 1,
                            profileImage = "",
                            name = "김민수",
                        ),
                    content = "안녕하세요",
                    date = LocalDate.now(),
                ),
            ),
    )
}
