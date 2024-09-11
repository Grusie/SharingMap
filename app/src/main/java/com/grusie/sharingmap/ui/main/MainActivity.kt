package com.grusie.sharingmap.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grusie.sharingmap.designsystem.theme.SharingMapTheme
import com.grusie.sharingmap.ui.navigation.main.MainBottomNavGraph
import com.grusie.sharingmap.ui.navigation.main.MainBottomNavItem
import com.grusie.sharingmap.ui.navigation.main.MainBottomNavigationBar
import com.grusie.sharingmap.ui.navigation.main.NavItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route
            val isBottomBarVisible = when {
                currentDestination?.startsWith(NavItem.Storage.screenRoute) == true -> false
                currentDestination?.equals(MainBottomNavItem.Search.screenRoute) == true -> false
                currentDestination?.startsWith(NavItem.User.screenRoute) == true -> false
                else -> true
            }
            SharingMapTheme {
                enableEdgeToEdge()
                Scaffold(
                    modifier = Modifier.windowInsetsPadding(
                        WindowInsets.systemBars.only(
                            WindowInsetsSides.Bottom
                        )
                    ),
                    bottomBar = {
                        if (isBottomBarVisible) {
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
                    },
                    content = {
                        MainBottomNavGraph(navController = navController) { finish() }
                    }
                )
            }
        }
    }
}