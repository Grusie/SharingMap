package com.grusie.sharingmap.ui.navigation.main

import com.grusie.sharingmap.data.STORAGE

sealed class NavItem(
    val title: String,
    val screenRoute: String,
) {
    data object Storage : NavItem(
        title = "Storage",
        screenRoute = STORAGE,
    )
}
