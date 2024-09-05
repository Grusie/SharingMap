package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.GrayA8AAAB
import com.grusie.sharingmap.designsystem.theme.Typography

@Composable
fun LimitedText(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val displayText = if (text.length > 50) {
        text.substring(0, 50) + "..."
    } else {
        text
    }

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Black)) {
            append(displayText)
        }
        if (text.length > 50) {
            withStyle(style = SpanStyle(color = GrayA8AAAB)) {
                append(" 더보기")
            }
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(tag = "더보기", start = offset, end = offset)
            .firstOrNull()?.let {
                onClick()
            }
    }, style = Typography.bodyMedium, modifier = modifier)

}