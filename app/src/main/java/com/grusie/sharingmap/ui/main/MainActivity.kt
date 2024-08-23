package com.grusie.sharingmap.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.grusie.sharingmap.ui.navigation.main.MainBottomNavGraph
import com.grusie.sharingmap.ui.navigation.main.MainBottomNavItem
import com.grusie.sharingmap.ui.navigation.main.MainBottomNavigationBar
import com.grusie.sharingmap.designsystem.theme.SharingMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SharingMapTheme {
                Scaffold(
                    bottomBar = {
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
                ) {
                    Box(Modifier.padding(it)) {
                        MainBottomNavGraph(navController) { finish() }
                    }
                }
            }
        }
    }
}