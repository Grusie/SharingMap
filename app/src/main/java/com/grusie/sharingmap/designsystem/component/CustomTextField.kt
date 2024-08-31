package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.R
import com.grusie.sharingmap.designsystem.theme.Gray9A9C9F
import com.grusie.sharingmap.designsystem.theme.GrayF1F4F7
import com.grusie.sharingmap.designsystem.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTextField(
    textFieldState: TextFieldState,
    hintText: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    cornerShape: RoundedCornerShape = RoundedCornerShape(12.dp),
    backgroundColor: Color = GrayF1F4F7,
) {
    BasicTextField2(
        state = textFieldState,
        modifier = modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        textStyle = Typography.headlineSmall.copy(color = Gray9A9C9F),
        decorator = { innerTextField ->
            Row(
                modifier =
                    modifier
                        .clip(shape = cornerShape)
                        .background(color = backgroundColor)
                        .padding(vertical = 15.dp)
                        .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.padding(start = 16.dp))
                if (textFieldState.text.isEmpty()) {
                    Text(
                        text = hintText,
                        style = Typography.headlineSmall,
                        color = Gray9A9C9F,
                    )
                }
                innerTextField()
            }
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun CustomTextFieldPreview() {
    CustomTextField(textFieldState = TextFieldState(initialText = ""), hintText = "")
}
