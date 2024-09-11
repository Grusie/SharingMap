package com.grusie.sharingmap.ui.common

import android.content.Context


fun Int.dpToPx(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}

fun Int.spToPx(context: Context): Float {
    return (this * context.resources.displayMetrics.scaledDensity)
}