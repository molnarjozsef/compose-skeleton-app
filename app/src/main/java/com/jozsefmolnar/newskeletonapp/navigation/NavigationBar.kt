package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.animation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    AnimatedVisibility(
        visible = currentRoute in listOf(
            Route.MainRoute.route,
            Route.SettingsRoute.route,
        ),
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {
        NavigationBar(modifier = modifier) {
            NavigationBarItem(
                selected = currentRoute == Route.MainRoute.route,
                onClick = { navController.navigate(Route.MainRoute.route) },
                label = { Text("Home") },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = null,
                    )
                }
            )
            NavigationBarItem(
                selected = currentRoute == Route.SettingsRoute.route,
                onClick = { navController.navigate(Route.SettingsRoute.route) },
                label = { Text("Settings") },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = null,
                    )
                }
            )
        }
    }
}
