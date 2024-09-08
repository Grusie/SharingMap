package com.grusie.sharingmap.ui.navigation.main

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.grusie.sharingmap.ui.main.edit.EditScreen
import com.grusie.sharingmap.ui.main.home.HomeScreen
import com.grusie.sharingmap.ui.main.map.MapScreen
import com.grusie.sharingmap.ui.main.mypage.MyPageScreen
import com.grusie.sharingmap.ui.main.mypage.storage.StorageScreen
import com.grusie.sharingmap.ui.main.search.SearchScreen
import com.grusie.sharingmap.ui.model.StorageUiModel

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
            MyPageScreen(navController = navController)
        }

        composable(NavItem.Storage.screenRoute + "?storage={storage}", arguments = listOf(navArgument("storage") { type = NavType.StringType })) {
            val storageJsonString = it.arguments?.getString("storage")
            val storage = Gson().fromJson(storageJsonString, StorageUiModel::class.java)
            StorageScreen(navController = navController, storage = storage)
        }
    }

    BackHandler {
        onBackPressed()
    }
}