package com.grusie.sharingmap.ui.main.home

import android.view.Gravity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.ModalStateItem
import com.grusie.sharingmap.designsystem.component.ModalTwoLinesItem
import com.grusie.sharingmap.designsystem.theme.Red
import com.grusie.sharingmap.designsystem.theme.SharingMapTheme
import com.grusie.sharingmap.designsystem.theme.White
@Composable
fun FeedModal(onDismiss: () -> Unit = {}, modifier: Modifier = Modifier) {
    var isChecked by remember { mutableStateOf(true) }

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
        dialogWindowProvider.window.setGravity(Gravity.BOTTOM or Gravity.CENTER)

        FeedModalContent(
            isChecked = isChecked,
            onCheckChanged = { isChecked = !isChecked },
            onClick = { /*TODO*/ },
            modifier = modifier.padding(bottom = 34.dp).padding(horizontal = 20.dp),
        )
    }
}

@Composable
fun FeedModalContent(
    isChecked: Boolean,
    onCheckChanged: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .clip(RoundedCornerShape(20.dp))
                .background(color = White)
                .padding(horizontal = 20.dp, vertical = 24.dp),
    ) {
        ModalStateItem(
            isChecked = isChecked,
            checkIcon = R.drawable.ic_follow,
            uncheckIcon = R.drawable.ic_unfollow,
            checkTitle = stringResource(R.string.feed_modal_follow_title),
            unCheckTitle = stringResource(R.string.feed_modal_unfollow_title),
            onCheckChanged = { onCheckChanged() },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        ModalTwoLinesItem(
            icon = R.drawable.ic_declaration,
            title = stringResource(id = R.string.feed_modal_declaration_title),
            description = stringResource(id = R.string.feed_modal_declaration_description),
            descriptionColor = Red,
            onClick = { onClick() },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FeedModalPreview() {
    SharingMapTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            FeedModal()
        }
    }
}
