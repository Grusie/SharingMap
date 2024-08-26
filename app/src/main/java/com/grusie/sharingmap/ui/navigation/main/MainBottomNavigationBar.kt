package com.grusie.sharingmap.ui.navigation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.grusie.sharingmap.designsystem.theme.White

@Composable
fun MainBottomNavigationBar(
    items: List<MainBottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomNavigation(
        backgroundColor = White,
        modifier = modifier.height(70.dp),
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Image(
                        if (currentRoute == item.screenRoute) painterResource(item.selectedIcon)
                        else painterResource(item.unselectedIcon),
                        contentDescription = stringResource(id = item.title)
                    )
                },

                selected = currentRoute == item.screenRoute,
                onClick = {
                    if (currentRoute != item.screenRoute) {
                        navController.navigate(item.screenRoute) {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}