package com.grusie.sharingmap.designsystem.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.White

object ToastUtil {
    @Composable
    fun ShowToast(
        toastViewModifier: Modifier = Modifier,
        fontSize: TextUnit = 14.sp,
        fontColor: Color = White,
        textModifier: Modifier = Modifier,
        resourceModifier: Modifier = Modifier.padding(end = 12.dp),
        messageTxt: String,
        resourceIcon: Int? = null
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = toastViewModifier
                .padding(bottom = 8.dp)
                .background(shape = RoundedCornerShape(32.dp), color = Black)
                .padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            resourceIcon?.let {
                Icon(
                    painter = painterResource(id = resourceIcon),
                    contentDescription = "toastIcon",
                    modifier = resourceModifier,
                    tint = White
                )
            }
            Text(
                fontSize = fontSize,
                modifier = textModifier,
                text = messageTxt,
                color = fontColor
            )
        }
    }
}