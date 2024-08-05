package com.grusie.sharingmap.ui.navigation.main

import com.grusie.sharingmap.R
import com.grusie.sharingmap.data.EDIT
import com.grusie.sharingmap.data.HOME
import com.grusie.sharingmap.data.MAP
import com.grusie.sharingmap.data.MYPAGE
import com.grusie.sharingmap.data.SEARCH

sealed class MainBottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    data object Home :
        MainBottomNavItem(R.string.main_bottom_nav_home, R.drawable.ic_launcher_foreground, HOME)

    data object Map :
        MainBottomNavItem(R.string.main_bottom_nav_map, R.drawable.ic_launcher_foreground, MAP)

    data object Edit :
        MainBottomNavItem(R.string.main_bottom_nav_edit, R.drawable.ic_launcher_foreground, EDIT)

    data object Search :
        MainBottomNavItem(
            R.string.main_bottom_nav_search,
            R.drawable.ic_launcher_foreground,
            SEARCH
        )

    data object MyPage :
        MainBottomNavItem(
            R.string.main_bottom_nav_mypage,
            R.drawable.ic_launcher_foreground,
            MYPAGE
        )
}