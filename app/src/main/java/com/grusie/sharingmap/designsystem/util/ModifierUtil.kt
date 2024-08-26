package com.grusie.sharingmap.designsystem.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Modifier.singleClickable(onClick: () -> Unit): Modifier {
    var duplicated by remember { mutableStateOf(false) }
    val timer = rememberCoroutineScope()

    return this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    ) {
        duplicated = true
        onClick()

        timer.launch {
            delay(1000)
            duplicated = false
        }
    }
}
