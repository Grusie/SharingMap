package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.GrayA8AAAB
import com.grusie.sharingmap.designsystem.theme.Typography

@Composable
fun LimitedText(
    text: String,
    isExpanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val displayText = if (text.length > 50) {
        if (isExpanded) text else text.substring(0, 50) + "..."
    } else {
        text
    }

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Black)) {
            append(displayText)
        }
        if (text.length > 50) {
            withStyle(style = SpanStyle(color = GrayA8AAAB)) {
                val startIndex = length
                append(if (isExpanded) "  접기" else "  더보기")
                addStringAnnotation(
                    tag = if (isExpanded) "접기" else "더보기",
                    annotation = "",
                    start = startIndex,
                    end = length
                )
            }
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(
            start = offset,
            end = offset
        ).firstOrNull()?.let {
            onClick()
        }
    }, style = Typography.bodyMedium, modifier = modifier)

}

@Preview
@Composable
private fun LimitedTextPreview() {
    var isExpanded by remember { mutableStateOf(false) }
    LimitedText(
        text = "안녕하세요. 김민수입니다. 잘 부탁드립니다. 안녕하세요. 김민수입니다. 잘 부탁드립니다. 안녕하세요. 김민수입니다. 잘 부탁드립니다. 안녕하세요. 김민수입니다. 잘 부탁드립니다",
        isExpanded = isExpanded,
        onClick = { isExpanded = !isExpanded })

}