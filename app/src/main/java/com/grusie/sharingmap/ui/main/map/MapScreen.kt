package com.grusie.sharingmap.ui.main.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MapScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Map Screen")
    }
}