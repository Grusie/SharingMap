package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.designsystem.theme.Gray9A9C9F
import com.grusie.sharingmap.designsystem.theme.GrayBABBBC
import com.grusie.sharingmap.designsystem.theme.Typography

@Composable
fun CustomOutLineButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(onClick = onClick, shape = RoundedCornerShape(8.dp), border = BorderStroke(1.dp, GrayBABBBC), modifier = modifier.fillMaxWidth()) {
        Text(text = text, style = Typography.bodyMedium, color = Gray9A9C9F)
    }
}

@Preview
@Composable
private fun CustomOutLineButtonPreview() {
    CustomOutLineButton(text = "테스트", onClick = {})
}