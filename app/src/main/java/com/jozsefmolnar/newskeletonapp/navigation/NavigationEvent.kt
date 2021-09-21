package com.jozsefmolnar.newskeletonapp.navigation

sealed class NavigationEvent {

    data class BackNavigation(
        val route: Route? = null,
        val result: NavigationResult? = null
    ) : NavigationEvent()

    data class ScreenNavigation(
        val route: Route,
        val navigationOptions: NavigationOptions? = null
    ) : NavigationEvent()
}
