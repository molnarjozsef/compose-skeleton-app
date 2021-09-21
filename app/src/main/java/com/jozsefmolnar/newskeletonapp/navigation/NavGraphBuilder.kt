package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.jozsefmolnar.newskeletonapp.navigation.screen.ScreenWithViewModel

fun <T : Route> NavGraphBuilder.composable(
    factory: Route.Factory<T>,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(route = factory.path, deepLinks = deepLinks) {
        ScreenWithViewModel(navBackStackEntry = it, routeFactory = factory) {
            content(it)
        }
    }
}

fun <T : Route> NavGraphBuilder.dialog(
    factory: Route.Factory<T>,
    deepLinks: List<NavDeepLink> = emptyList(),
    cancelable: Boolean = true,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    val dialogProperties = DialogProperties(
        dismissOnBackPress = cancelable,
        dismissOnClickOutside = cancelable
    )

    dialog(route = factory.path, deepLinks = deepLinks, dialogProperties = dialogProperties) {
        ScreenWithViewModel(navBackStackEntry = it, routeFactory = factory) {
            content(it)
        }
    }
}

inline fun NavGraphBuilder.navigation(
    startDestination: Route.Factory<*>,
    route: Route.Factory<*>,
    builder: NavGraphBuilder.() -> Unit
) = this.navigation(startDestination.path, route.path, builder)
