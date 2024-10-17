package com.grusie.sharingmap.ui.main.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.grusie.sharingmap.ui.main.map.MapBottomSheetDragHandle

@Composable
fun EditLocationModal() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight.dp)
    ) {
        MapBottomSheetDragHandle()
    }
}