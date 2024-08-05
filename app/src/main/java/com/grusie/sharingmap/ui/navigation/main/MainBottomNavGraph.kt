package com.grusie.sharingmap.ui.navigation.main

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grusie.sharingmap.ui.main.edit.EditScreen
import com.grusie.sharingmap.ui.main.home.HomeScreen
import com.grusie.sharingmap.ui.main.map.MapScreen
import com.grusie.sharingmap.ui.main.mypage.MyPageScreen
import com.grusie.sharingmap.ui.main.search.SearchScreen

@Composable
fun MainBottomNavGraph(navController: NavHostController, onBackPressed: () -> Unit) {
    NavHost(navController = navController, startDestination = MainBottomNavItem.Home.screenRoute) {
        composable(MainBottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(MainBottomNavItem.Map.screenRoute) {
            MapScreen()
        }
        composable(MainBottomNavItem.Edit.screenRoute) {
            EditScreen()
        }
        composable(MainBottomNavItem.Search.screenRoute) {
            SearchScreen()
        }
        composable(MainBottomNavItem.MyPage.screenRoute) {
            MyPageScreen()
        }
    }

    BackHandler {
        onBackPressed()
    }
}