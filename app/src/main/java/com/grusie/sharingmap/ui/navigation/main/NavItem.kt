package com.grusie.sharingmap.ui.navigation.main

import com.grusie.sharingmap.data.FEEDCOLLECTION
import com.grusie.sharingmap.data.SEARCHMAP
import com.grusie.sharingmap.data.USER

sealed class NavItem(
    val title: String,
    val screenRoute: String,
) {
    data object FeedCollection : NavItem(
        title = "FeedCollection",
        screenRoute = FEEDCOLLECTION,
    )

    data object User : NavItem(
        title = "User",
        screenRoute = USER,
    )

    data object SearchMap : NavItem(
        title = "SearchMap",
        screenRoute = SEARCHMAP
    )
}
