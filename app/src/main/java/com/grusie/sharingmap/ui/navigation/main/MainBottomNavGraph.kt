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
import com.grusie.sharingmap.ui.main.map.SearchMapScreen
import com.grusie.sharingmap.ui.main.mypage.MyPageRoute
import com.grusie.sharingmap.ui.main.mypage.archivecollection.ArchiveCollectionScreen
import com.grusie.sharingmap.ui.main.mypage.user.UserRoute
import com.grusie.sharingmap.ui.main.search.SearchRoute
import com.grusie.sharingmap.ui.model.StorageUiModel
import com.grusie.sharingmap.ui.model.TagUiModel

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
            EditScreen(navController = navController)
        }
        composable(MainBottomNavItem.Search.screenRoute) {
            SearchRoute(
                onNavigationClick = { navController.popBackStack() },
                onUserItemClick = {
                    navController.navigate(
                        NavItem.User.screenRoute + "?userId=${
                            Gson().toJson(
                                it
                            )
                        }"
                    )
                },
                onTagItemClick = {
                    navController.navigate(
                        NavItem.FeedCollection.screenRoute + "?tag=${
                            Gson().toJson(
                                it
                            )
                        }"
                    )
                }
            )
        }
        composable(MainBottomNavItem.MyPage.screenRoute) {
            MyPageRoute(onUserClick = {
                navController.navigate(NavItem.User.screenRoute + "?user=${Gson().toJson(it)}")
            }, onStorageClick = {
                navController.navigate(
                    NavItem.FeedCollection.screenRoute + "?storage=${
                        Gson().toJson(
                            it
                        )
                    }"
                )
            })
        }

        composable(
            NavItem.FeedCollection.screenRoute + "?storage={storage}",
            arguments = listOf(navArgument("storage") { type = NavType.StringType })
        ) {
            val storageJsonString = it.arguments?.getString("storage")
            val storage = Gson().fromJson(storageJsonString, StorageUiModel::class.java)
            ArchiveCollectionScreen(navController = navController, storage = storage, tag = null)
        }

        composable(
            NavItem.FeedCollection.screenRoute + "?tag={tag}",
            arguments = listOf(navArgument("tag") { type = NavType.StringType })
        ) {
            val tagJsonString = it.arguments?.getString("tag")
            val tag = Gson().fromJson(tagJsonString, TagUiModel::class.java)
            ArchiveCollectionScreen(navController = navController, storage = null, tag = tag)
        }

        composable(
            NavItem.User.screenRoute + "?userId={userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) {
            val userId = it.arguments?.getString("userId")?.toLong()
            if (userId != null) {
                UserRoute(
                    userId = userId,
                    onNavigateClick = { navController.popBackStack() },
                    onUserClick = {
                        navController.navigate(
                            NavItem.User.screenRoute + "?userId=${
                                Gson().toJson(
                                    it
                                )
                            }"
                        )
                    },
                    onStorageClick = {
                        navController.navigate(
                            NavItem.FeedCollection.screenRoute + "?storage=${
                                Gson().toJson(
                                    it
                                )
                            }"
                        )
                    }
                )
            }
            /*UserScreen(navController = navController, user = user)*/
        }

        composable(
            NavItem.SearchMap.screenRoute,
        ) {
            SearchMapScreen(navController = navController)
        }
    }

    BackHandler {
        onBackPressed()
    }
}