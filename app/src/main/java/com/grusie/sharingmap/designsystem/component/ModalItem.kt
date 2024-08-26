package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Gray100
import com.grusie.sharingmap.designsystem.theme.Red
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.util.singleClickable

@Composable
fun ModalStateItem(
    isChecked: Boolean,
    checkIcon: Int,
    uncheckIcon: Int,
    checkTitle: String,
    unCheckTitle: String,
    onCheckChanged: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = Gray100)
                .singleClickable { onCheckChanged() }
                .padding(vertical = 16.dp, horizontal = 20.dp),
    ) {
        Image(
            painter = painterResource(id = if (isChecked) checkIcon else uncheckIcon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = if (isChecked) checkTitle else unCheckTitle,
            style = Typography.titleLarge,
            color = Black,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}

@Composable
fun ModalTwoLinesItem(
    icon: Int,
    title: String,
    description: String,
    descriptionColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = Gray100)
                .singleClickable { onClick() },
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 16.dp),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.padding(vertical = 10.5.dp)) {
            Text(
                text = title,
                style = Typography.titleSmall,
                color = Black,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                style = Typography.bodyMedium,
                color = descriptionColor,
            )

        }
    }
}

@Preview
@Composable
private fun ModalStateItemPreview() {
    var isChecked by remember { mutableStateOf(true) }
    ModalStateItem(
        isChecked = isChecked,
        checkIcon = R.drawable.ic_follow,
        uncheckIcon = R.drawable.ic_unfollow,
        checkTitle = "팔로우",
        unCheckTitle = "팔로우 취소",
        onCheckChanged = { isChecked = !isChecked },
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
private fun ModalTwoLinesItemPreview() {
    ModalTwoLinesItem(
        icon = R.drawable.ic_declaration,
        title = "신고",
        description = "해당 유저 게시물 노출이 제한됩니다.",
        descriptionColor = Red,
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
    )
}
