package com.jozsefmolnar.newskeletonapp.navigation.impl

import com.jozsefmolnar.newskeletonapp.navigation.NavigationOptions
import com.jozsefmolnar.newskeletonapp.navigation.NavigationResult
import com.jozsefmolnar.newskeletonapp.navigation.Navigator
import com.jozsefmolnar.newskeletonapp.navigation.Route
import com.jozsefmolnar.newskeletonapp.navigation.RouteManager
import javax.inject.Inject

class NavigatorImpl @Inject constructor(private val routeManager: RouteManager) : Navigator {

    override fun navigateToHome(navigationOptions: NavigationOptions?) =
        navigateTo(routeManager.getDefaultRoute(), navigationOptions)

    override fun navigateTo(route: Route, navigationOptions: NavigationOptions?) =
        routeManager.setRoute(route, navigationOptions)

    override fun navigateBack(result: NavigationResult?) =
        routeManager.goBack(result)

    override fun navigateBackTo(route: Route, result: NavigationResult?) =
        routeManager.goBackTo(route, result)

    override fun navigateBackToHome(result: NavigationResult?) =
        routeManager.goBackTo(routeManager.getDefaultRoute(), result)
}
