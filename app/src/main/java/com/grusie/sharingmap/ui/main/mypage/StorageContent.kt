package com.grusie.sharingmap.ui.main.mypage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.R
import com.grusie.sharingmap.data.fakeStorage
import com.grusie.sharingmap.designsystem.component.CustomExpandableTextField
import com.grusie.sharingmap.designsystem.component.ModalTwoLinesItem
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Blue
import com.grusie.sharingmap.designsystem.theme.Gray8D8D8E
import com.grusie.sharingmap.designsystem.theme.GrayE8EAEB
import com.grusie.sharingmap.designsystem.theme.GrayF8FAFC
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.ui.model.StorageUiModel

@Composable
fun StorageLazyColumn(
    isOwnUser: Boolean,
    storages: List<StorageUiModel>,
    onAddClick: () -> Unit,
    onClick: (StorageUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        if (isOwnUser) {
            item {
                ModalTwoLinesItem(
                    icon = R.drawable.ic_plus,
                    title = stringResource(id = R.string.mypage_storages_add_title),
                    description = stringResource(id = R.string.mypage_storages_add_text),
                    descriptionColor = Blue,
                    backgroundColor = GrayF8FAFC,
                    onClick = onAddClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 14.dp)
                )
            }
        }
        items(storages.size) {
            StorageItem(
                storage = storages[it],
                onClick = { onClick(storages[it]) },
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 14.dp)
            )
        }
    }

}

@Composable
fun StorageItem(storage: StorageUiModel, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 10.5.dp, horizontal = 20.dp)
        .clickable { onClick() }) {
        Text(text = storage.title, style = Typography.titleMedium, color = Black)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = stringResource(id = R.string.mypage_storage_description_text, storage.count),
            style = Typography.bodySmall,
            color = Gray8D8D8E
        )

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewStorageContent(
    textFieldState: TextFieldState,
    isLock: Boolean,
    onLockClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 20.dp, vertical = 24.dp)) {
        Text(text = stringResource(id = R.string.mypage_storage_title), style = Typography.bodySmall, color = Black)
        Spacer(modifier = Modifier.height(6.dp))
        HorizontalDivider(thickness = 1.dp, color = GrayE8EAEB)
        CustomExpandableTextField(textFieldState = textFieldState, hintText = stringResource(id = R.string.mypage_storage_title_hint_text), modifier = modifier.padding(16.dp))
        Image(
            painter = painterResource(id = if (isLock) R.drawable.btn_lock else R.drawable.btn_unlock),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 6.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onLockClick()
                }
        )
        HorizontalDivider(thickness = 1.dp, color = GrayE8EAEB)
    }

}

@Preview
@Composable
private fun StoragePreview() {
    StorageLazyColumn(
        isOwnUser = true,
        storages = fakeStorage,
        onAddClick = { /*TODO*/ },
        onClick = {})
}