package com.grusie.sharingmap.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.Gray9A9C9F
import com.grusie.sharingmap.designsystem.theme.GrayE8E9EA
import com.grusie.sharingmap.designsystem.theme.Typography
import com.grusie.sharingmap.designsystem.theme.White
import com.grusie.sharingmap.designsystem.util.singleClickable
import com.grusie.sharingmap.ui.model.ArchiveType

@Composable
fun FeedRadioGroup(
    options: List<ArchiveType>,
    selectedType: ArchiveType,
    onClick: (ArchiveType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .padding(top = 6.dp, bottom = 16.dp)
                .padding(horizontal = 14.dp),
    ) {
        options.forEach { option ->
            CustomRadioButton(
                title = option.title,
                textColor = if (option == selectedType) White else Gray9A9C9F,
                backgroundColor = if (option == selectedType) Black else GrayE8E9EA,
                onClick = { onClick(option) },
                modifier = Modifier.padding(end = 8.dp),
            )
        }
    }
}

@Composable
fun CustomRadioButton(
    title: String,
    textColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = Typography.bodyMedium,
        color = textColor,
        textAlign = TextAlign.Center,
        modifier =
            modifier
                .width(65.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .singleClickable { onClick() }
                .background(color = backgroundColor)
                .padding(vertical = 7.5.dp),
    )
}
