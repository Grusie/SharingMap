package com.grusie.sharingmap.ui.navigation.main

import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainBottomNavigationBar(
    items: List<MainBottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    BottomNavigation(
        modifier = modifier,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Image(
                        painterResource(item.icon),
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