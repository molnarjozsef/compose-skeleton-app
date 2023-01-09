package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jozsefmolnar.newskeletonapp.feature.NavGraphs
import com.jozsefmolnar.newskeletonapp.feature.destinations.HomeScreenDestination
import com.jozsefmolnar.newskeletonapp.feature.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.spec.Route
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import kotlinx.coroutines.flow.map

@Composable
fun AppNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier) {
        BottomBarDestination.values().forEach { destination ->
            val isCurrentDestOnBackStack by navController.isRouteOnBackStackAsState(destination.direction)

            NavigationBarItem(
                selected = isCurrentDestOnBackStack,
                onClick = {
                    if (isCurrentDestOnBackStack) {
                        navController.popBackStack(destination.direction, false)
                        return@NavigationBarItem
                    }

                    navController.navigate(destination.direction.route) {
                        popUpTo(NavGraphs.root) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(destination.icon, contentDescription = destination.label) },
                label = { Text(destination.label) },
            )
        }
    }
}

private enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    val label: String,
) {
    Home(HomeScreenDestination, Icons.Default.Home, "Home"),
    Settings(SettingsScreenDestination, Icons.Default.Settings, "Settings"),
}

private fun NavController.isRouteOnBackStackFlow(route: Route) =
    currentBackStackEntryFlow.map { isRouteOnBackStack(route) }

@Composable
private fun NavController.isRouteOnBackStackAsState(route: Route) =
    isRouteOnBackStackFlow(route).collectAsState(initial = false)
