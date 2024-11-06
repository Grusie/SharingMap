package com.grusie.sharingmap.ui.main.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomOutLineButton
import com.grusie.sharingmap.designsystem.component.UserInfo
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Gray9A9C9F
import com.grusie.sharingmap.designsystem.theme.GrayE6E6E6
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.WhiteFBFBFB
import com.grusie.sharingmap.ui.model.UserUiModel

@Composable
fun MyUserInfo(user: UserUiModel, modifier: Modifier = Modifier) {
    Column {
        UserInfo(user = user)
        Spacer(modifier = Modifier.height(12.dp))
        /*CustomOutLineButton(
            text = "프로필 수정",
            onClick = { *//*TODO*//* },
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 12.dp, bottom = 16.dp)
        )*/
    }
}

@Composable
fun OtherUserInfo(
    isFollow: Boolean,
    onFollowClick: () -> Unit,
    onMapClick: () -> Unit,
    user: UserUiModel,
    modifier: Modifier = Modifier
) {
    Column {
        UserInfo(user = user)
        Spacer(modifier = Modifier.height(12.dp))
       /* Row(modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 12.dp, bottom = 16.dp)) {
            FollowButton(
                isFollow = isFollow,
                onClick = onFollowClick,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(13.dp))
            CustomOutLineButton(
                text = "지도 보기",
                onClick = onMapClick,
                modifier = Modifier.weight(1f)
            )
        }*/
    }
}


@Composable
fun FollowButton(isFollow: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    if (isFollow) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            modifier = modifier,
            colors = ButtonColors(
                containerColor = GrayE6E6E6,
                contentColor = GrayE6E6E6,
                disabledContainerColor = GrayE6E6E6,
                disabledContentColor = GrayE6E6E6
            )
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(1.dp))
                Text(text = "팔로우", style = Typography.bodyMedium, color = Gray9A9C9F)
            }
        }
    } else {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            modifier = modifier,
            colors = ButtonColors(
                containerColor = Black,
                contentColor = Black,
                disabledContainerColor = Black,
                disabledContentColor = Black
            )
        ) {
            Text(text = "팔로우", style = Typography.bodyMedium, color = WhiteFBFBFB)
        }

    }


}

@Preview
@Composable
private fun MyPageUserInfoPreview() {
    MyUserInfo(
        user = UserUiModel(
            id = 1,
            name = "김민수",
            profileImage = "",
            followerCount = 100,
            postCount = 100,
            description = "안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/",
        )
    )
}

@Preview
@Composable
private fun OtherUserInfoPreview() {
    var isFollow by remember { mutableStateOf(false) }

    OtherUserInfo(
        user = UserUiModel(
            id = 1,
            name = "김민수",
            profileImage = "",
            followerCount = 100,
            postCount = 100,
            description = "안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/",
        ),
        isFollow = isFollow,
        onFollowClick = { isFollow = !isFollow },
        onMapClick = {}
    )

}