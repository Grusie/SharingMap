package com.grusie.sharingmap.ui.main.edit

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.component.CustomBottomSheetDragHandle
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Blue458FFF
import com.grusie.sharingmap.designsystem.theme.Gray9A9C9F
import com.grusie.sharingmap.designsystem.theme.GrayE6E6E6
import com.grusie.sharingmap.designsystem.theme.GrayE8EAEB_30
import com.grusie.sharingmap.designsystem.theme.GrayF1F4F7
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.designsystem.util.ToastUtil
import com.grusie.sharingmap.ui.model.AdditionalAttachUiModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPlaceBottomSheet(
    editPlaceUiState: EditPlaceUiState = EditPlaceUiState(),
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = {},
    onSaveClick: (String) -> Unit = {},
    onLockClick: (Boolean) -> Unit = {},
    showToast: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    setAttachList: (List<AdditionalAttachUiModel>) -> Unit
) {

    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        containerColor = White,
        dragHandle = { CustomBottomSheetDragHandle() },
    ) {
        Box() {
            EditPlaceBottomSheetContent(
                editPlaceUiState = editPlaceUiState,
                modifier = modifier,
                onDismiss = onDismiss,
                onSaveClick = onSaveClick,
                onLockClick = onLockClick,
                showToast = showToast,
                setAttachList = setAttachList,
            )
            if (editPlaceUiState.isToastShow) {
                ToastUtil.ToastView(
                    toastViewModifier = Modifier
                        .align(Alignment.BottomCenter),
                    fontColor = White,
                    messageTxt = stringResource(id = editPlaceUiState.toastMsgId)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditPlaceBottomSheetContent(
    editPlaceUiState: EditPlaceUiState = EditPlaceUiState(),
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onSaveClick: (String) -> Unit = {},
    onLockClick: (Boolean) -> Unit = {},
    showToast: (Int) -> Unit = {},
    setAttachList: (List<AdditionalAttachUiModel>) -> Unit = {}
) {
    val placeTextFieldState =
        rememberTextFieldState(editPlaceUiState.additionalArchiveUiModel.placeName)
    val contentTextFieldState = rememberTextFieldState()
    val scrollState = rememberScrollState()

    val pickMultipleMedia =
        rememberLauncherForActivityResult(
            if (editPlaceUiState.additionalAttachList.size >= 9)
                ActivityResultContracts.PickVisualMedia()
            else ActivityResultContracts.PickMultipleVisualMedia(10 - editPlaceUiState.additionalAttachList.size)
        ) { uris ->

            if (uris is List<*>) {
                if (uris.isNotEmpty()) {
                    setAttachList(ArrayList(editPlaceUiState.additionalAttachList).apply {
                        uris.forEach {
                            add(
                                AdditionalAttachUiModel(
                                    id = editPlaceUiState.additionalAttachList.size.toLong(),
                                    src = it.toString()
                                )
                            )
                        }
                    })
                }
            } else {
                setAttachList(ArrayList(editPlaceUiState.additionalAttachList).apply {
                    add(
                        AdditionalAttachUiModel(
                            id = editPlaceUiState.additionalAttachList.size.toLong(),
                            src = uris.toString()
                        )
                    )
                })
            }
        }

    Column()
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = R.string.custom_bottom_sheet_cancel_text),
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 20.dp)
                    .clickable { onDismiss() }
            )

            Text(
                text = stringResource(id = R.string.edit_information_title),
                fontSize = 18.sp,
                fontWeight = FontWeight(weight = 700),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 4.dp)
            )

            Text(
                text = stringResource(id = R.string.custom_bottom_sheet_save_text),
                fontSize = 14.sp,
                color = Blue458FFF,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 20.dp)
                    .clickable { onSaveClick(contentTextFieldState.text.toString()) }
            )
        }


        LaunchedEffect(contentTextFieldState.text) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .verticalScroll(state = scrollState)
        ) {
            Text(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(12.dp),
                        color = GrayF1F4F7
                    )
                    .padding(vertical = 17.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                text = editPlaceUiState.additionalArchiveUiModel.address,
                fontWeight = FontWeight(400)
            )

            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 6.dp),
                text = stringResource(id = R.string.edit_place_title),
                fontSize = 13.sp,
                fontWeight = FontWeight(700),
            )

            HorizontalDivider(color = GrayE6E6E6)

            CustomTextField(
                modifier = Modifier.padding(12.dp),
                textFieldState = placeTextFieldState,
                hintText = stringResource(id = R.string.edit_place_hint)
            )

            HorizontalDivider(color = GrayE6E6E6)

            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 6.dp),
                text = stringResource(id = R.string.edit_content_title),
                fontSize = 13.sp,
                fontWeight = FontWeight(700),
            )

            HorizontalDivider(color = GrayE6E6E6)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CustomTextField(
                    textFieldState = contentTextFieldState,
                    hintText = stringResource(id = R.string.edit_content_hint),
                    keyboardOptions = KeyboardOptions.Default,
                    lineLimits = TextFieldLineLimits.MultiLine(7),
                )

                AttachListView(
                    editPlaceUiState.additionalAttachList
                ) { attachUiModel ->
                    setAttachList(ArrayList(editPlaceUiState.additionalAttachList).apply {
                        remove(attachUiModel)
                    })
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = {
                        if (editPlaceUiState.additionalAttachList.size < 10)
                            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        else {
                            showToast(R.string.edit_toast_attach_error_10)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gallery),
                        contentDescription = "edit_ic_gallery",
                        tint = Gray9A9C9F
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = {
                        val isPublic = !editPlaceUiState.additionalArchiveUiModel.isPublic
                        showToast(if (isPublic) R.string.edit_toast_public else R.string.edit_toast_private)
                        onLockClick(isPublic)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = if (!editPlaceUiState.additionalArchiveUiModel.isPublic) R.drawable.ic_lock_close else R.drawable.ic_lock_open),
                        tint = Gray9A9C9F,
                        contentDescription = "edit_ic_gallery"
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))
            HorizontalDivider(color = GrayE6E6E6)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTextField(
    textFieldState: TextFieldState,
    hintText: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
) {
    BasicTextField2(
        state = textFieldState,
        keyboardOptions = keyboardOptions,
        textStyle = Typography.bodyLarge.copy(
            color = Black,
            lineHeight = TextUnit(21f, TextUnitType.Sp)
        ),
        lineLimits = lineLimits,
        modifier = modifier.fillMaxWidth(),
        decorator = { innerTextField ->

            if (textFieldState.text.isEmpty()) {
                Text(
                    text = hintText,
                    style = Typography.headlineSmall,
                    color = Gray9A9C9F,
                )
            }
            innerTextField()
        }
    )
}

@Composable
fun AttachListView(
    attachList: List<AdditionalAttachUiModel> = emptyList(),
    removeAttachItem: (AdditionalAttachUiModel) -> Unit
) {
    if (attachList.isNotEmpty())
        LazyRow(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        ) {
            items(attachList) {
                AttachView(it, removeAttachItem)
            }
        }
}

@Composable
fun AttachView(
    attachUiModel: AdditionalAttachUiModel,
    removeAttachItem: (AdditionalAttachUiModel) -> Unit = {}
) {
    Row() {
        Box(
            modifier = Modifier
                .width(153.dp)
                .height(122.dp)
                .background(shape = RoundedCornerShape(12.dp), color = Black)
        ) {
            AsyncImage(
                model = attachUiModel.src,
                contentDescription = "attach_item",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .background(shape = CircleShape, color = GrayE8EAEB_30)
                    .size(24.dp)
                    .padding(2.dp),
                onClick = {
                    removeAttachItem(attachUiModel)
                }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = White,
                    contentDescription = "ic_image_remove"
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
    }
}


@Composable
@Preview(showBackground = true)
fun EditPlaceBottomSheetPreview() {
    EditPlaceBottomSheetContent()
}

@Composable
@Preview(showBackground = true)
fun AttachViewPreview() {
    AttachView(AdditionalAttachUiModel(id = 0, src = ""))
}