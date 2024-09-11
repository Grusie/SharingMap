package com.grusie.sharingmap.ui.navigation.main

import com.grusie.sharingmap.data.STORAGE
import com.grusie.sharingmap.data.USER

sealed class NavItem(
    val title: String,
    val screenRoute: String,
) {
    data object Storage : NavItem(
        title = "Storage",
        screenRoute = STORAGE,
    )

    data object User : NavItem(
        title = "User",
        screenRoute = USER,
    )
}
