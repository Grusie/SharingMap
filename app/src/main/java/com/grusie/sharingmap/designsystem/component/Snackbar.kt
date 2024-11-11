package com.grusie.sharingmap.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grusie.sharingmap.designsystem.theme.Black
import com.grusie.sharingmap.designsystem.theme.White

@Composable
fun Snackbar(data: SnackbarData, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Box(Modifier.fillMaxSize()) {
            Text(
                text = data.visuals.message,
                color = Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.align(Alignment.Center),
                letterSpacing = -(0.24).sp,
            )
        }
    }
}