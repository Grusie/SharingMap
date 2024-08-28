package com.grusie.sharingmap.designsystem.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.GrayE6E6E6
import com.grusie.sharingmap.designsystem.theme.SharingMapTheme
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.ui.model.CommentUiModel
import com.grusie.sharingmap.ui.model.UserUiModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet(
    title: String,
    content: @Composable () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        containerColor = White,
        dragHandle = null,
    ) {
        Column(modifier = modifier.fillMaxWidth().wrapContentHeight()) {
            HorizontalDivider(
                modifier =
                    Modifier
                        .width(39.dp)
                        .padding(top = 10.dp, bottom = 20.dp)
                        .align(Alignment.CenterHorizontally),
                thickness = 5.dp,
                color = GrayE6E6E6,
            )
            Text(
                text = title,
                style = Typography.displayMedium,
                color = Black,
                modifier =
                    modifier
                        .padding(vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
            )
            content()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CustomBottomSheetPreview(modifier: Modifier = Modifier) {
    var showBottomSheet by remember { mutableStateOf(true) }

    SharingMapTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            if (showBottomSheet) {
                CustomBottomSheet(title = "댓글", content = {
                    CommentContent(
                        comments =
                            listOf(
                                CommentUiModel(
                                    id = 1,
                                    user =
                                        UserUiModel(
                                            id = 1,
                                            profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                                            name = "김민수",
                                        ),
                                    content = "안녕하세요",
                                    date = LocalDate.now(),
                                ),
                                CommentUiModel(
                                    id = 1,
                                    user =
                                        UserUiModel(
                                            id = 1,
                                            profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                                            name = "김민수",
                                        ),
                                    content = "안녕하세요",
                                    date = LocalDate.now(),
                                ),
                                CommentUiModel(
                                    id = 1,
                                    user =
                                        UserUiModel(
                                            id = 1,
                                            profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                                            name = "김민수",
                                        ),
                                    content = "안녕하세요",
                                    date = LocalDate.now(),
                                ),
                                CommentUiModel(
                                    id = 1,
                                    user =
                                        UserUiModel(
                                            id = 1,
                                            profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                                            name = "김민수",
                                        ),
                                    content = "안녕하세요",
                                    date = LocalDate.now(),
                                ),
                                CommentUiModel(
                                    id = 1,
                                    user =
                                        UserUiModel(
                                            id = 1,
                                            profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                                            name = "김민수",
                                        ),
                                    content = "안녕하세요",
                                    date = LocalDate.now(),
                                ),
                                CommentUiModel(
                                    id = 1,
                                    user =
                                        UserUiModel(
                                            id = 1,
                                            profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                                            name = "김민수",
                                        ),
                                    content = "안녕하세요",
                                    date = LocalDate.now(),
                                ),
                                CommentUiModel(
                                    id = 1,
                                    user =
                                        UserUiModel(
                                            id = 1,
                                            profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                                            name = "김민수",
                                        ),
                                    content = "안녕하세요",
                                    date = LocalDate.now(),
                                ),
                                CommentUiModel(
                                    id = 1,
                                    user =
                                        UserUiModel(
                                            id = 1,
                                            profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                                            name = "김민수",
                                        ),
                                    content = "안녕하세요",
                                    date = LocalDate.now(),
                                ),
                            ),
                    )
                }, onDismiss = { showBottomSheet = false })
            }
        }
    }
}