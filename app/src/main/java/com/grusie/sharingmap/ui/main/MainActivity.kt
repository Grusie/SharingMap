package com.grusie.sharingmap.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grusie.sharingmap.ui.navigation.main.MainBottomNavGraph
import com.grusie.sharingmap.ui.navigation.main.MainBottomNavItem
import com.grusie.sharingmap.ui.navigation.main.MainBottomNavigationBar
import com.grusie.sharingmap.designsystem.theme.SharingMapTheme
import com.grusie.sharingmap.ui.main.mypage.storage.StorageScreen
import com.grusie.sharingmap.ui.navigation.main.NavItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route
            var isBottomBarVisible = when {
                currentDestination?.startsWith(NavItem.Storage.screenRoute) == true -> false
                else -> true
            }
            SharingMapTheme {
                Scaffold(
                    bottomBar = {
                        if(isBottomBarVisible) {
                            MainBottomNavigationBar(
                                items = listOf(
                                    MainBottomNavItem.Home,
                                    MainBottomNavItem.Map,
                                    MainBottomNavItem.Edit,
                                    MainBottomNavItem.Search,
                                    MainBottomNavItem.MyPage
                                ), navController = navController
                            )
                        }
                    }
                ) {
                    Box(Modifier.padding(it)) {
                        MainBottomNavGraph(navController = navController) { finish() }
                    }
                }
            }
        }
    }
}